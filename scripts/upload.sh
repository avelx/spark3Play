#!/bin/bash

cd ..

sbt clean

sbt assembly
#sbt assemblyPackageDependency

scp -P 2222 -i ~/.ssh/cluster/id_rsa  /Users/pavel/devcore/playground/spark3Play/target/scala-2.12/job-runner.jar pavel@192.168.0.8:/home/pavel/.
#scp -P 2222 -i ~/.ssh/cluster/id_rsa  /Users/pavel/devcore/playground/spark3Play/target/scala-2.12/spark3Play-assembly-0.1-deps.jar pavel@192.168.0.8:/home/pavel/.

#scp -P 2222 -i ~/.ssh/cluster/id_rsa  /System/Volumes/Data/Users/pavel/.ivy2/cache/org.scala-lang/scala-library/jars/scala-library-2.12.10.jar pavel@192.168.0.8:/home/pavel/.