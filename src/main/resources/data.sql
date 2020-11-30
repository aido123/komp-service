replace into level (id, name, description) values(1, 'Level 1 - Enter the Castle', 'Please go to the initial  before continuing to the next levels');
replace into level (id, name, description) values(2, 'Level 2 - Begin the Quest', 'In this level you will start learning the basic concepts of kubernetes');
 
replace into question (id, levelid, description, setup, answer_query, answer_value, type) values(1, 2, '<p>In this question, your task is to create a kubernetes docker secret.</p><p>A docker secret is used by kubernetes to pull your docker images from a private docker registry like , when creating your deployments. It consists of a username, password and the docker registry you want to pull from.</p><p>Your task is to create a kubernetes docker secret called regcred in your kubernetes namespace.</p><p><strong>Tip</strong></p><p>See <a href="https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/#create-a-secret-by-providing-credentials-on-the-command-line">https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/#create-a-secret-by-providing-credentials-on-the-command-line</a><br/>Do not forget to put your namespace in the command when creating the doker secret<p><p>Click Setup to get your namespace and environment setup.</p><p>Once you have created your kubernetes docker secret, then Validate to capture your flag.</p>', '', 'kubectl get secret regcred -o jsonpath={.metadata.name}', 'regcred', 'setup' );


replace into question (id, levelid, description, setup, answer_query, answer_value, type) values(2, 2, '<p>What command would you use to get all the pods in your namespace</p><p>Select one of the answers shown below, then click Validate to capture your flag.</p>', '', '', '', 'nosetup' );
 
replace into answer (id, qid, description, correct) values(1, 2, 'kubectl get pods -n namespace', 'true');
replace into answer (id, qid, description, correct) values(2, 2, 'kubectl get ships => namespace', 'false');
replace into answer (id, qid, description, correct) values(3, 2, 'kubectl pods -n namespace', 'false');


replace into question (id, levelid, description, setup, answer_query, answer_value, type) values(3, 2, '<p>In this question we will deploy a secret to your namespace. Your task is to identity the name of this secret.</p><p>Click setup to setup your namespace and environment.</p><p>Then select one of the answers shown below, then click Validate to capture your flag.</p>', '{    "kind": "Secret",    "apiVersion": "v1",    "metadata": {        "name": "yolosecret",        "creationTimestamp": null    },    "data": {        "secret1": "eW9sbw=="    }}', '', '', 'setup' );

replace into answer (id, qid, description, correct) values(4, 3, 'mysupersecret', 'false');
replace into answer (id, qid, description, correct) values(5, 3, 'yolosecret', 'true');
replace into answer (id, qid, description, correct) values(6, 3, 'secret123', 'false');

 
replace into question (id, levelid, description, setup, answer_query, answer_value, type) values(4, 2, '<p>How many pods are in your namespace?</p><p>Pods are the smallest deployable units of computing that you can create and manage in Kubernetes. Read more: <a href="https://kubernetes.io/docs/concepts/workloads/pods/">https://kubernetes.io/docs/concepts/workloads/pods/</a></p><p>Click setup to setup your namespace and environment.</p><p>Then select one of the answers shown below, then click Validate to capture your flag.</p>', '{"apiVersion": "apps/v1","kind": "Deployment","metadata": {"name": "nginx"},"spec": {"selector": {"matchLabels": {"app": "nginx"}},"replicas": 2,"template": {"metadata": {"labels": {"app": "nginx"}},"spec": {"containers": [{"name": "nginx","image": "nginx:1.15","ports": [{"containerPort": 80}]}]}}}}', '', '', 'setup' );

replace into answer (id, qid, description, correct) values(7, 4, '1', 'false');
replace into answer (id, qid, description, correct) values(8, 4, '2', 'true');
replace into answer (id, qid, description, correct) values(9, 4, '3', 'false');
replace into answer (id, qid, description, correct) values(10, 4, '4', 'false');
replace into answer (id, qid, description, correct) values(11, 4, '5', 'false');
replace into answer (id, qid, description, correct) values(12, 4, '6', 'false');
replace into answer (id, qid, description, correct) values(13, 4, '7', 'false');
replace into answer (id, qid, description, correct) values(14, 4, '8', 'false');


replace into question (id, levelid, description, setup, answer_query, answer_value, type) values(5, 2, '<p>In this task you are required to create a new pod with the following details: name: mypod, image: nginx:1.15. <p><p>NOTE: nginx:1.15 is cached on each node, so you do not need to worry about docker credentials to pull it from docker.</p><p>TIP: The run command is your friend <a href="https://kubernetes.io/docs/reference/kubectl/cheatsheet/#interacting-with-running-pods">https://kubernetes.io/docs/reference/kubectl/cheatsheet/#interacting-with-running-pods</a>. It can be used to create a deployment, job, cronjob, and a pod. The synatx for creating a pod on the command like is like kubectl run NAME_OF_POD --image=DOCKER_IMAGE --restart=Never</p><p>Click setup to setup your namespace and environment.</p><p>Then click Validate to capture your flag.</p>', '', 'kubectl get pod mypod -o jsonpath={.metadata.name}', 'mypod', 'setup' );


replace into question (id, levelid, description, setup, answer_query, answer_value, type) values(6, 2, '<p>How many pods are in your namespace now?</p></p><p>Click setup to setup your namespace and environment.</p><p>Then select one of the answers shown below, then click Validate to capture your flag.</p>', '{"apiVersion": "apps/v1","kind": "Deployment","metadata": {"name": "nginx"},"spec": {"selector": {"matchLabels": {"app": "nginx"}},"replicas": 1,"template": {"metadata": {"labels": {"app": "nginx"}},"spec": {"containers": [{"name": "nginx","image": "nginx:1.15","ports": [{"containerPort": 80}]}]}}}}', '', '', 'setup' );

replace into answer (id, qid, description, correct) values(15, 6, '1', 'true');
replace into answer (id, qid, description, correct) values(16, 6, '2', 'false');
replace into answer (id, qid, description, correct) values(17, 6, '3', 'false');
replace into answer (id, qid, description, correct) values(18, 6, '4', 'false');
replace into answer (id, qid, description, correct) values(19, 6, '5', 'false');
replace into answer (id, qid, description, correct) values(20, 6, '6', 'false');
replace into answer (id, qid, description, correct) values(21, 6, '7', 'false');
replace into answer (id, qid, description, correct) values(22, 6, '8', 'false');

replace into user (id, name, oid, email) values(1, 'Jack Daw', 'cedafee1-edc9-4986-98a1-8aa8954da0eb', 'jackdaw@adrianhyneshotmail.onmicrosoft.com');