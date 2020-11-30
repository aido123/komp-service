package pri.hynes.aks.comp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.google.gson.Gson; 
import com.google.gson.JsonObject;
import com.microsoft.azure.spring.autoconfigure.aad.UserPrincipal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpStatusCodeException;
import java.util.Arrays;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@CrossOrigin(origins = "*")
@RestController
public class SetupController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_CompGroup')")
    @PostMapping(value = "/api/setup/levels/{lid}/questions/{qid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String setup(@PathVariable("lid") long lid, @PathVariable("qid") long qid, Authentication authentication) {

        String oid = (String)((UserPrincipal)(authentication.getPrincipal())).getClaim("oid");
        String email = (String)((UserPrincipal)(authentication.getPrincipal())).getClaim("preferred_username");

        String setup = (String) jdbcTemplate.queryForObject("select setup from question where id = ?",
                new Object[] { qid },
                String.class);

        String namespaceToDelete = runKubectlBinary("kubectl get ns -l comp=true,oid="+oid+" -o=jsonpath={.items[0].metadata.name}");
        System.out.println(namespaceToDelete);
        if (namespaceToDelete != "" && oid.equals(namespaceToDelete)){
            runKubectlBinary("kubectl delete ns "+namespaceToDelete);
        }

        runKubectlBinary("kubectl create ns "+oid);
        runKubectlBinary("kubectl label ns "+oid+" comp=true oid="+oid);

        try{
            Path tempFile = Files.createTempFile(null, null);
            //TODO create aks-namespace-admin
            String rolebinding = "{   \"apiVersion\": \"rbac.authorization.k8s.io/v1\",   \"kind\": \"RoleBinding\",   \"metadata\": {      \"name\": \""+oid+"\"   },   \"roleRef\": {      \"apiGroup\": \"rbac.authorization.k8s.io\",      \"kind\": \"ClusterRole\",      \"name\": \"admin\"   },   \"subjects\": [      {         \"apiGroup\": \"rbac.authorization.k8s.io\",         \"kind\": \"User\",         \"name\": \""+email+"\"      }   ]}";
            Files.write(tempFile, rolebinding.getBytes(StandardCharsets.UTF_8));
            String rb = runKubectlBinary("kubectl apply -f "+ tempFile +" -n "+oid);
            System.out.println(rb);
        }
        catch(Exception e)
        {
       e.printStackTrace();
       return "{\"status\":\"500\", \"message\":\"Internal Error\"}";
        }

	if (!"".equals(setup)){
            try{
                Path tempFile = Files.createTempFile(null, null);
                Files.write(tempFile, setup.getBytes(StandardCharsets.UTF_8));
                runKubectlBinary("kubectl apply -f "+ tempFile +" -n "+oid);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return "{\"status\":\"500\", \"message\":\"Internal Error\"}";
            }
        }

        String msg = "Question "+qid+" is now setup and ready. Please connect to your namespace with the az cli and kubectl. az aks get-credentials --resource-group <Resource Group> --name <cluster name>  kubectl get pods -n " + oid;
        System.out.println(msg);
        return "{\"status\":\"200\", \"message\":\""+msg+"\"}";
    }


    //TODO API all this!
    private String runKubectlBinary(String command){
        System.out.println(command);
        String output = "";
        try{
            Runtime rt = Runtime.getRuntime();

            String[] commands;

            if (SystemUtils.IS_OS_WINDOWS){
                commands = new String[]{"cmd.exe", "/c", command};
            }else{
                commands = new String[]{"/bin/sh", "-c", command};
            }

            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(proc.getErrorStream()));
            
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                output += s;
            }

            while ((s = stdError.readLine()) != null) {
                output += s;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Output: "+output);
        }

        return output;
    }
}
