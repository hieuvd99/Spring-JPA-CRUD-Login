1. Thiết lập kết nối giữa server với github										
- Tạo key trên server									
- Copy public key (.pub) vào GitHub → Settings → Deploy Keys									
- Server sẽ dùng private key (file ko có .pub) để pull code từ repo																	
- Kiểm tra key trên server: *ls -la ~/.ssh*							
- Tạo file ~/.ssh/config để cấu hình dùng private key:
```
Host github.com  
HostName github.com  
User git  
IdentityFile ~/.ssh/id_github
```

2. Clone code: *git clone git@github.com:username/repo.git .*

3. Tạo cicd file yml										
- Nếu server không có public IP, github sẽ không SSH vào đc server => dùng Self-hosted Runner trên server  
- Tạo file systemd service để tự động run lại ứng dụng khi restart app:  
- Tạo file: *sudo nano /etc/systemd/system/myapp.service*
```
[Unit]  
Description=CRUD-Login Spring Boot App  
After=network.target  

[Service]  
User=hieuvd  
WorkingDirectory=/home/hieuvd/actions-runner/_work/CI-CD_GithubAction_JavaProject/CI-CD_GithubAction_JavaProject  
ExecStart=/usr/bin/java -jar target/CRUD-Login-0.0.1-SNAPSHOT.jar  
SuccessExitStatus=143  
Restart=always  
RestartSec=5  
StandardOutput=file:/home/hieuvd/app.log  
StandardError=file:/home/hieuvd/app.err.log  

[Install]  
WantedBy=multi-user.target  
```
- Reload và enable service:	
```
sudo systemctl daemon-reload
sudo systemctl enable myapp
sudo systemctl start myapp
sudo systemctl status myapp
```
- Có thể cần cài đặt visudo để user runner có thể restart app mà ko cần pass								
