# Download and unpack hadoop on the set of hosts
declare -a hosts=("192.168.0.38" "192.168.0.68" "192.168.0.78")

# Look along hosts
HADOOP_RELEASE_URL="https://apache.mirrors.nublue.co.uk/hadoop/common/hadoop-3.2.2/hadoop-3.2.2.tar.gz"
SSH_REMOTE_HOST="ssh -i ~/.ssh/garage/hadoop.avel.local"

for host in "${hosts[@]}"
do
  #STEP 1
  download_result=$(eval "$SSH_REMOTE_HOST hadoop@$host curl $HADOOP_RELEASE_URL -o hadoop-3.2.2.tar.gz")
  echo "$download_result"

  #STEP 2
  unpack_result=$(eval "$SSH_REMOTE_HOST hadoop@$host tar -xf hadoop-3.2.2.tar.gz")
  echo "$unpack_result"

done