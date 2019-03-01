<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="zh-CN">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="${(options.description)!}">
    <meta name="author" content="${(options.admin.name)!}">

    <title>${(options.siteTitle)!"yogo"} - <@spring.message "install.title"/></title>

    <!-- Custom fonts for this template-->
    <link href="/yg-content/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/yg-content/admin/css/sb-admin-2.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4"><@spring.message "install.title"/></h1>
                        </div>

                        <form class="user" action="/yg-admin/install" method="post" id="register_form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="siteTitle"
                                       name="siteTitle"
                                       placeholder="<@spring.message "register.site-title"/>" required>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-user" id="email"
                                       name="email"
                                       placeholder="<@spring.message "email"/>" required>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" class="form-control form-control-user" id="name"
                                           name="username"
                                           placeholder="<@spring.message "username"/>" maxlength="32" required>
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control form-control-user" id="displayName"
                                           name="displayName"
                                           placeholder="<@spring.message "display-name"/>" maxlength="32">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user"
                                           name="password"
                                           id="password" placeholder="<@spring.message "password"/>" maxlength="32" required>
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user"
                                           id="rePassword" placeholder="<@spring.message "password-confirm"/>" maxlength="32"
                                           onchange="check_pass()"
                                           required>
                                </div>
                            </div>
                            <input id="submit" type="submit" class="btn btn-primary btn-user btn-block"
                                   value="<@spring.message "install.submit"/>"/>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="/yg-content/admin/vendor/jquery/jquery.min.js"></script>
<script src="/yg-content/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/yg-content/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/yg-content/admin/js/sb-admin-2.min.js"></script>

<script>
    function check_pass() {
        let pass = document.getElementById('password');
        let re_pass = document.getElementById('rePassword');
        document.getElementById('submit').disabled = re_pass.value !== pass.value;
    }
</script>

</body>

</html>
