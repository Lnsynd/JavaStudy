function askVerifyCode() {
    get('http://192.168.124.128:8080/api/auth/verify-code', {
        email: $("#input-email").val(),
    }, function (data) {
        if (data.code !== 200) {
            alert(data.reason)
        }
    })
}
// http://192.168.124.128:8080
// http://localhost:8080


function login() {
    post('http://192.168.124.128:8080/api/auth/login', {
        username: $("#username").val(),
        password: $("#password").val()
    }, function (data) {
        if (data.code === 200) {
            window.location = "index.html";
        } else {
            alert(data.reason)
        }
    })
}

function logout() {
    get('http://192.168.124.128:8080/api/auth/logout',{},function (data) {
        if (data.code === 200) {
            window.location = "login.html";
        } else {
            alert(data.reason)
        }
    })
}


function initUserInfo() {
    get('http://192.168.124.128:8080/api/user/info',{}, function (data) {
        if (data.code === 200) {
            alert("登录成功！欢迎" + data.data.username + "进入图书管理系统!")
        } else {
            window.location = "login.html";
        }
    })
}


function get(url, data,success) {
    $.ajax({
        type: "get",
        url: url,
        data: data,
        async: true,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}

function post(url, data, success) {
    $.ajax({
        type: "post",
        url: url,
        async: true,
        data: data,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: success
    });
}



