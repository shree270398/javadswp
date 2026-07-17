Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/jammy64"

  # Manager Node
  config.vm.define "manager" do |manager|
    manager.vm.network "private_network", ip: "192.168.56.10"

    manager.vm.provider "virtualbox" do |vb|
      vb.memory = 2048
    end

    manager.vm.provision "shell", inline: <<-SHELL
      apt update
      apt install -y docker.io
      systemctl enable docker
      systemctl start docker

      usermod -aG docker vagrant

      docker swarm init --advertise-addr 192.168.56.10
    SHELL
  end


  # Worker 1
  config.vm.define "worker1" do |worker|
    worker.vm.network "private_network", ip: "192.168.56.11"

    worker.vm.provider "virtualbox" do |vb|
      vb.memory = 1024
    end

    worker.vm.provision "shell", inline: <<-SHELL
      apt update
      apt install -y docker.io
      systemctl enable docker
      systemctl start docker
      usermod -aG docker vagrant
    SHELL
  end


  # Worker 2
  config.vm.define "worker2" do |worker|
    worker.vm.network "private_network", ip: "192.168.56.12"

    worker.vm.provider "virtualbox" do |vb|
      vb.memory = 1024
    end

    worker.vm.provision "shell", inline: <<-SHELL
      apt update
      apt install -y docker.io
      systemctl enable docker
      systemctl start docker
      usermod -aG docker vagrant
    SHELL
  end


  # Worker 3
  config.vm.define "worker3" do |worker|
    worker.vm.network "private_network", ip: "192.168.56.13"

    worker.vm.provider "virtualbox" do |vb|
      vb.memory = 1024
    end

    worker.vm.provision "shell", inline: <<-SHELL
      apt update
      apt install -y docker.io
      systemctl enable docker
      systemctl start docker
      usermod -aG docker vagrant
    SHELL
  end

end
