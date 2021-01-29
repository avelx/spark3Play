# Download and unpack hadoop on the set of hosts
declare -a hosts=("192.168.0.38" "192.168.0.68" "192.168.0.78")

# Look along hosts
SSH_REMOTE_HOST="ssh -i ~/.ssh/garage/hadoop.avel.local"
HADOOP_HOME="/home/hadoop/hadoop-3.2.2"

for host in "${hosts[@]}"
do
  #STEP 1: add variable
  add_var_result=$(eval "ssh -i ~/.ssh/garage/hadoop.avel.local hadoop@$host echo 12 >>~/.bash_profile")
  echo "$add_var_result"

  #STEP 2: load variable
  load_var_result=$(eval "ssh -i ~/.ssh/garage/hadoop.avel.local hadoop@$host source ~/.bash_profile")
  echo "$load_var_result"

done


firewall-cmd --zone=public --permanent --add-port=50020/tcp
firewall-cmd --zone=public --permanent --add-port=50070/tcp
firewall-cmd --zone=public --permanent --add-port=9000/tcp
firewall-cmd --zone=public --permanent --add-port=8020/tcp
firewall-cmd --zone=public --permanent --add-port=9870/tcp
firewall-cmd --zone=public --permanent --add-port=9864/tcp
firewall-cmd --zone=public --permanent --add-port=9866/tcp
firewall-cmd --zone=public --permanent --add-port=9867/tcp
firewall-cmd --zone=public --permanent --add-port=44758/tcp
firewall-cmd --zone=public --permanent --add-port=50010/tcp

firewall-cmd --zone=public --permanent --add-port=8080/tcp
firewall-cmd --zone=public --permanent --add-port=7077/tcp
firewall-cmd --zone=public --permanent --add-port=8081/tcp

firewall-cmd --zone=public --permanent --add-port=33682/tcp