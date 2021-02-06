declare -a hosts=("192.168.0.38" "192.168.0.68" "192.168.0.78")

# Look along hosts
HIVE_RELEASE_URL="https://apache.mirrors.nublue.co.uk/hive/hive-3.1.2/apache-hive-3.1.2-bin.tar.gz"

SSH_REMOTE_HOST="ssh -i ~/.ssh/garage/hadoop.avel.local"

for host in "${hosts[@]}"
do
  #STEP 1
  download_result=$(eval "$SSH_REMOTE_HOST hadoop@$host curl $HIVE_RELEASE_URL -o apache-hive-3.1.2-bin.tar.gz")
  echo "$download_result"

  #STEP 2
  unpack_result=$(eval "$SSH_REMOTE_HOST hadoop@$host tar -xf apache-hive-3.1.2-bin.tar.gz")
  echo "$unpack_result"

done

/*
  https://issues.apache.org/jira/browse/HIVE-22915
  - Next command to fix issue with HIV kick start

  rm $HIVE_HOME/lib/guava-19.0.jar
  cp $HADOOP_HOME/share/hadoop/hdfs/lib/guava-27.0-jre.jar $HIVE_HOME/lib

  Start HIV:
  $HIVE_HOME/bin/hive


ls -d $HIVE_HOME/meta*
/opt/hive-3.1.0-bin/metastore_db

 rm -rf $HIVE_HOME/metastore_db
$ cd $HIVE_HOME
$ schematool -initSchema -dbType derby
*/