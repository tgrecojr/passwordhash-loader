# passwordhash-loader

This is a simple Spring Boot based application that loads SHA1 password hashes from the latest https://haveibeenpwned.com/Passwords list into a MySQL database.

The intent here is to then write a password generating API for my peronal use that checks the generated password against this list before returning so that no already compromised passowrds are used.

This example also uses the Spring @Async annotations.

  
### Tech


* Spring Boot
* Spring JDBC
* Spring Async
* MySql


