
function login()
{
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            response = JSON.parse(xmlhttp.response);
            if (response["error"])
                document.getElementById("response").innerHTML = response["message"];
            else
            {
                document.getElementById("Username").value = document.getElementById("Username").value.toLowerCase();
                document.getElementById("responseForProfilePage").value = response["message"];
                document.getElementById("formLogin").submit();
            }
        }
    };
    var params = "username=" + document.getElementById("Username").value + "&password=" + document.getElementById("Password").value;
    xmlhttp.open("POST", "../API/v1/API.php?apicall=login", true);
    xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlhttp.send(params);
}
function signUp()
{
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            response = JSON.parse(xmlhttp.response);
            if (response["error"])
                document.getElementById("response").innerHTML = response["message"];
            else
            {
                document.getElementById("Username").value = document.getElementById("Username").value.toLowerCase();
                document.getElementById("responseForProfilePage").value = response["message"];
                document.getElementById("formLogin").submit();
            }
        }
    };
    var params = "username=" + document.getElementById("Username").value + "&password=" + document.getElementById("Password").value;
    xmlhttp.open("POST", "../API/v1/API.php?apicall=signUp", true);
    xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlhttp.send(params);
}