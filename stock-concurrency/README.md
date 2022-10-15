# 테스트 환경

원활한 실행/테스트를 위해 MySQL, Redis 가 컴퓨터에 설치되어 있어야 한다.    
Docker를 사용 중이라면, 아래 docker-compose.yml 을 실행하면 편하다.   

```yaml
version: "3"

services:
  mysql:
    platform: linux/x86_64
    image: mysql:8.0.27
    container_name: local-mysql
    ports:
      - 3306:3306
    volumes:
      - ./mysql/conf.d:/etc/mysql/conf.d
      - ./mysql/data:/var/lib/mysql
      - ./mysql/initdb.d:/docker-entrypoint-initdb.d
    env_file: ./mysql/.env
    environment:
      MYSQL_ROOT_PASSWORD: password
      TZ: Asia/Seoul

  redis:
    image: redis
    command: redis-server --port 6379
    container_name: local-redis
    labels:
      - "name=redis"
      - "mode=standalone"
    ports:
      - 6379:6379
```
