<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test page</title>
</head>
<body>
<img src="yogo/assets/logo.png" alt="logo">
<h1>Hello World!</h1>
<#list posts.content as post >
    <h2>${post.title}</h2>
    <h3>This post tag has <#list post.tag as t>${t.name}<#sep>,</#list></h3>
    <p>${post.body}</p>
</#list>
</body>
</html>