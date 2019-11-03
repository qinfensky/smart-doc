<h1 align="center"><a href="https://github.com/shalousun/smart-doc" target="_blank">Smart-Doc Project</a></h1>

## Introduce
Smart-doc is a java restful api document generation tool. Smart-doc is based on interface source code analysis to generate interface documents, and zero annotation intrusion.
You only need to write java standard comments when developing, smart-doc can help you generate a simple and clear markdown
Or a static html document. If you are tired of the numerous annotations and strong intrusion code contamination of document tools like swagger, then hug smart-doc!
## Features
- Zero annotation, zero learning cost, only need to write standard java document comments.
- Automatic derivation based on source code interface definition, powerful return structure derivation support.
- Support Spring MVC, Spring Boot, Spring Boot Web Flux (Controller mode writing).
- Supports the derivation of asynchronous interface returns such as Callable, Future, CompletableFuture.
- Support JAVA's JSR303 parameter verification specification.
- Support for automatic generation of request examples based on request parameters.
- Support for generating json return value examples.
- Support for loading source code from outside the project to generate field comments (including the sources jar package).
- Support for generating multiple formats of documents: Markdown, HTML5, Asciidoctor.
## Getting started
[Smart-doc Samples](https://github.com/shalousun/smart-doc-demo.git)。
```
# git clone https://github.com/shalousun/smart-doc-demo.git
```
This example already provides a static html document generated in advance. You can start the Spring Boot project and then go directly to `http://localhost:8080/doc/api.html` to view the interface documentation generated by smart-doc.
Of course you can also browse `http://localhost:8080/doc/api.html`, 
which looks a html like generated by `asciidoctor-maven-plugin` plugin.
### Maven dependency
```
<dependency>
    <groupId>com.github.shalousun</groupId>
    <artifactId>smart-doc</artifactId>
    <version>1.7.4</version>
    <scope>test</scope>
</dependency>
```
### Create a unit test
Just running a unit test will allow Smart-doc to generate a very concise api document for you, 
which is much simpler than swagger.
```
/**
 * @author yu 2018/06/11.
 */
public class ApiDocTest {

    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:8080");
        
        //If the strict mode is set to true, Smart-doc forces that the public method in each interface in the code has a comment.
        config.setStrict(true);
        
        //When AllInOne is set to true, the document generation of all interfaces is merged into a Markdown or AsciiDoc document,
        // and the error code list is output to the bottom of the document.
        config.setAllInOne(true);
        
        //Set the api document output path.
        config.setOutPath("d:\\md");
        
        //since 1.7.5
        //corverd old AllIneOne.md  file generated by smart-doc.
        config.setCoverOld(true);
        
        //since 1.7.5
        //set project name
        config.setProjectName("Your project name");
        // If you do not configure PackageFilters, it matches all controllers by default.
        // Configure multiple controller paths to be separated by commas
        config.setPackageFilters("com.power.doc.controller");
        
        //Set the request header, if there is no request header, you don't need to set it.
        config.setRequestHeaders(
                ApiReqHeader.header().setName("access_token").setType("string")
                        .setDesc("Basic auth credentials").setRequired(true).setSince("v1.1.0"),
                ApiReqHeader.header().setName("user_uuid").setType("string").setDesc("User Uuid key")
        );
        
    
        //Output a list of error codes in the project, using for example:
        List<ApiErrorCode> errorCodeList = new ArrayList<>();
        for (ErrorCodeEnum codeEnum : ErrorCodeEnum.values()) {
            ApiErrorCode errorCode = new ApiErrorCode();
            errorCode.setValue(codeEnum.getCode()).setDesc(codeEnum.getDesc());
            errorCodeList.add(errorCode);
        }
        //not necessary
        config.setErrorCodes(errorCodeList);
    
        //Set the document change record,
        //it is not necessary to have the document change record take effect only when setAllInOne is set to true.
        config.setRevisionLogs(
                RevisionLog.getLog().setRevisionTime("2018/12/15").setAuthor("chen").setRemarks("test").setStatus("create").setVersion("V1.0"),
                RevisionLog.getLog().setRevisionTime("2018/12/16").setAuthor("chen2").setRemarks("test2").setStatus("update").setVersion("V2.0")
        );
    
        long start = System.currentTimeMillis();
        //Generating Markdown documentation
        ApiDocBuilder.builderControllersApi(config);
        
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}
```
### Generated document example
#### Interface head rendering
(https://images.gitee.com/uploads/images/2018/0905/173104_abcf4345_144669.png "1.png")
#### Request parameter example rendering
(https://images.gitee.com/uploads/images/2018/0905/172510_853735b9_144669.png "2.png")
#### Response parameter example renderings
(https://images.gitee.com/uploads/images/2018/0905/172538_1918820c_144669.png "3.png")
## Building
you can build with the following commands. (Java 1.8 is required to build the master branch)
```
mvn clean install -Dmaven.test.skip=true
```
## Other reference
- [Smart-doc manual](https://github.com/shalousun/smart-doc/wiki)
## License
Smart-doc is under the Apache 2.0 license.  See the [LICENSE](https://github.com/shalousun/smart-doc/blob/master/license.txt) file for details.
## Contact
Email： 836575280@qq.com
