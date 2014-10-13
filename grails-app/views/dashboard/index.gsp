<%@ page import="com.kaufda.mubs.model.BlogEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Mubs</title>
	</head>
	<body>
        <div class="container">
            <div class="jumbotron">
                <h2 style="text-align: center;">Mubs: For Your Blogging Needs!</h2>
            </div>
            <div class="row">
                <div class="col-md-8">
                    
                    <h1><g:message code="blog.posts.label" default="All Blog Posts" /></h1>

                    <g:if test="${blogEntriesCount > 0}">
                        <h6>Enjoy our ${blogEntriesCount} blog entries!</h6>
                    </g:if>
                    <g:else>
                        <h6>We have no blog entries now. Please come back some other time. Thank you.</h6>
                    </g:else>

                    <g:if test="${flash.message}">
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>${flash.message}</strong>
                        </div>
                    </g:if>

                    <g:hasErrors bean="${blogEntriesInstanceList}">
                        <ul class="errors" role="alert">
                            <g:eachError bean="${blogEntriesInstanceList}" var="error">
                                <li
                                    <g:if test="${error in org.springframework.validation.FieldError}">
                                        data-field-id="${error.field}"
                                    </g:if>>
                                    <div class="alert alert-danger">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <strong>${error}</strong>
                                    </div>
                                </li>
                            </g:eachError>
                        </ul>
                    </g:hasErrors>


                    <g:form url="[controller:'blogEntry', action:'blogEntryDetail']" >
                        <g:each in="${blogEntriesInstanceList}" status="i" var="blogEntryInstance">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h1>Title: ${blogEntryInstance?.title}</h1>
                                    <h3>Blog: ${blogEntryInstance?.blog?.name}</h3>
                                    <h4>Author: ${blogEntryInstance?.blog?.user?.toString()}</h4>
                                    <h5>Date: ${blogEntryInstance?.lastUpdated}</h5>
                                </div>
                                <div class="panel-body">
                                    <p>
                                        ${blogEntryInstance?.content.substring(0, 10)}
                                    </p>
                                    <hr>

                                    <div>
                                        <g:link controller="blogEntry" action="blogEntryDetail" id="${blogEntryInstance.id}" name="readMore"
                                                class="btn btn-info">${message(code: 'read.more.button.label', default: 'Read More')}</g:link>
                                    </div>
                                </div>
                            </div>
                        </g:each>
                    </g:form>
                </div>

                <div class="col-md-4">

                    <g:each in="${blogEntriesInstanceList}" status="i" var="blogEntryInstance">
                        <ul class="list-group">
                            <li class="list-group-item">
                            <span class="badge">${blogEntryInstance?.numberOfVisits}</span>

                                <g:link controller="blogEntry" action="blogEntryDetail" id="${blogEntryInstance.id}" name="readDetail">
                                    ${blogEntryInstance?.title}
                                </g:link>
                            </li>
                        </ul>
                    </g:each>
                </div>
            </div>

            <div class="pagination">
                <g:paginate total="${blogEntriesCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
