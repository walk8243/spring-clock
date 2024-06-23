# Spring Clock

java.time.Clock for Spring Boot

## Usage

### build.gradle

```build.gradle
repositories {
	maven {
		url 'https://maven.pkg.github.com/walk8243/spring-clock'
		credentials {
			username = System.getenv('GITHUB_USERNAME')
			password = System.getenv('GITHUB_TOKEN')
		}
	}
}

dependencies {
  implementation 'xyz.walk8243:spring-clock:+'
}
```

### application.yaml

```yaml
spring:
  clock:
    timezone-id: Asia/Tokyo # e.g. Europe/London, EST
    mock-datetime: 2007-12-03T10:15:30
```
