# qhull3d

Java 17 adaptation of quickhull3d code by John E. Lloyd

The modifications include

* suggestions by IntelliJ IDE
* suggestions by Eclipse IDE
* remove unused functions
* reordering of functions inside classes
* strict separation of main and test scope

## Integration

From time to time, a version is deployed and made available for maven integration. Specify `repository` and `dependency` of the library `qhull3d` in the `pom.xml` file of your maven project:

```xml
<dependencies>
  <!-- other dependencies -->
  <dependency>
    <groupId>ch.alpine</groupId>
    <artifactId>qhull3d</artifactId>
    <version>0.0.2</version>
  </dependency>
</dependencies>

<repositories>
  <!-- other repositories -->
  <repository>
    <id>qhull3d-mvn-repo</id>
    <url>https://raw.github.com/datahaki/qhull3d/mvn-repo/</url>
    <snapshots>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </snapshots>
  </repository>
</repositories>
```