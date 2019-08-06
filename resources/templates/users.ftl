<html>
    <body>
    <div>
        <h1>${authenticatedUserName}</h1>
    </div>
        <ul>
            <#list users as user>
                <li>${user}</li>
            </#list>
        </ul>
    </body>
</html>