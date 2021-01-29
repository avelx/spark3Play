#scp -i ~/.ssh/garage/hadoop.avel.local ~/Downloads/binance_crypto-klines.zip hadoop@delta.avel.local:/home/hadoop/datasets
# Download and unpack hadoop on the set of hosts
declare -a hosts=("192.168.0.38" "192.168.0.68" "192.168.0.78")

# Look along hosts
SPARK_RELEASE_URL="https://apache.mirrors.nublue.co.uk/spark/spark-3.0.1/spark-3.0.1-bin-hadoop2.7.tgz"

for host in "${hosts[@]}"
do
  #STEP 1
  download_result=$(eval "ssh -i ~/.ssh/garage/spark.avel.local spark@$host curl $SPARK_RELEASE_URL -o spark-3.0.1-bin-hadoop2.7.tgz")
  echo "$download_result"

  #STEP 2
  unpack_result=$(eval "ssh -i ~/.ssh/garage/spark.avel.local spark@$host tar -xf spark-3.0.1-bin-hadoop2.7.tgz")
  echo "$unpack_result"

done