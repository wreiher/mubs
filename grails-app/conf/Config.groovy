// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

/**
 * KEEPING APPLICATION LOG IN A FILE
 */
// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    appenders {

        environments {

            development {

                String tempDirPath = System.getProperty("java.io.tmpdir")

                if(!tempDirPath.endsWith(File.separator))
                    tempDirPath += File.separator

                rollingFile name: "file", maxFileSize: 5000000,
                        file: "${tempDirPath}/mubs.log",
                        threshold: org.apache.log4j.Level.DEBUG

                console name: "stdout", threshold: org.apache.log4j.Level.DEBUG

                console name: "stacktrace", threshold: org.apache.log4j.Level.DEBUG
            }

            test {

                String tempDirPath = System.getProperty("java.io.tmpdir")

                if(!tempDirPath.endsWith(File.separator))
                    tempDirPath += File.separator

                rollingFile name: "file", maxFileSize: 5000000,
                        file: "${tempDirPath}/mubs-prod.log",
                        threshold: org.apache.log4j.Level.DEBUG

                console name: "stdout", threshold: org.apache.log4j.Level.DEBUG

                console name: "stacktrace", threshold: org.apache.log4j.Level.DEBUG

                /*
                //If the application will be deployed in a Linux-based server
                rollingFile name: "file", maxFileSize: 5000000,
                        file: "/var/log/mubs.log",
                        threshold: org.apache.log4j.Level.DEBUG


                rollingFile name: "stacktrace", maxFileSize: 5000000,
                        file: "/var/log/mubs-stacktrace.log",
                        threshold: org.apache.log4j.Level.DEBUG
                */
            }

            production {

                String tempDirPath = System.getProperty("java.io.tmpdir")

                if(!tempDirPath.endsWith(File.separator))
                    tempDirPath += File.separator

                rollingFile name: "file", maxFileSize: 5000000,
                        file: "${tempDirPath}/mubs-prod.log",
                        threshold: org.apache.log4j.Level.DEBUG

                console name: "stdout", threshold: org.apache.log4j.Level.DEBUG

                console name: "stacktrace", threshold: org.apache.log4j.Level.DEBUG

                /*
                //If the application will be deployed in a Linux-based server

                rollingFile name: "file", maxFileSize: 5000000,
                        file: "/var/log/mubs.log",
                        threshold: org.apache.log4j.Level.DEBUG

                rollingFile name: "stacktrace", maxFileSize: 5000000,
                        file: "/var/log/mubs-stacktrace.log",
                        threshold: org.apache.log4j.Level.DEBUG
                */
            }
        }
    }

    environments {

        development {

            debug 'file': ['com.kaufda.mubs', 'grails.app.services.com.kaufda.mubs.services', 'grails.app.controllers.com.kaufda.mubs.controllers']

            warn 'stdout': ['com.kaufda.mubs', 'grails.app.services.com.kaufda.mubs.services', 'grails.app.controllers.com.kaufda.mubs.controllers']
        }

        test {
            info 'file': ['com.kaufda.mubs', 'grails.app.services.com.kaufda.mubs.services', 'grails.app.controllers.com.kaufda.mubs.controllers']
        }

        production {
            warn 'file': ['com.kaufda.mubs', 'grails.app.services.com.kaufda.mubs.services', 'grails.app.controllers.com.kaufda.mubs.controllers']
        }

    }

    root {
        // send the root logger to 'file' appender instead of console
        error 'file'
    }

    error 'file': ['org.codehaus.groovy.grails.web.servlet',  //  controllers
                   'org.codehaus.groovy.grails.web.pages', //  GSP
                   'org.codehaus.groovy.grails.web.sitemesh', //  layouts
                   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
                   'org.codehaus.groovy.grails.web.mapping', // URL mapping
                   'org.codehaus.groovy.grails.commons', // core / classloading
                   'org.codehaus.groovy.grails.plugins', // plugins
                   'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
                   'org.springframework',
                   'org.hibernate',
                   'net.sf.ehcache.hibernate',
                   'org.springframework.security',
                   'grails.app']

}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.kaufda.mubs.model.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.kaufda.mubs.model.UserRole'
grails.plugin.springsecurity.authority.className = 'com.kaufda.mubs.model.Role'
grails.plugin.springsecurity.requestMap.className = 'com.kaufda.mubs.model.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'

// Additional SpringSecurity Configurations
// Enable logout without using HTTP method POST
grails.plugin.springsecurity.logout.postOnly = false