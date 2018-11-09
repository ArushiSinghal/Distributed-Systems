cat a.txt | python mapper.py | sort -k1,1 | python reducer.py

bin/hadoop jar streaming.jar -mapper /home/hduser/assi5/mapper.py -reducer /home/hduser/assi5/reducer.py -input /user/hduser/gutenberg/* -output /user/hduser/gutenberg-output1111

# RUN HADOOP #
su - hduser
cat $HOME/.ssh/id_rsa.pub >> $HOME/.ssh/authorized_keys
ssh localhost

# INSTALL HADOOP #

wget http://mirrors.sonic.net/apache/hadoop/common/hadoop-2.6.5/hadoop-2.6.5.tar.gz

tar xvzf hadoop-2.6.5.tar.gz

sudo mkdir -p /usr/local/hadoop

# IMPORTANT LINKS #
https://www.bogotobogo.com/Hadoop/BigData_hadoop_Install_on_ubuntu_16_04_single_node_cluster.php
https://tecadmin.net/install-oracle-java-6-jdk-6-ubuntu-via-ppa/
https://www.michael-noll.com/tutorials/writing-an-hadoop-mapreduce-program-in-python/
