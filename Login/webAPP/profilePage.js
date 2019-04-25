
function changePassword()
{
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            response = JSON.parse(xmlhttp.response);
            if (response["error"])
            {
                document.getElementById("response").classList.remove("text-success");
                document.getElementById("response").classList.add("text-danger");
            }
            else
            {
                document.getElementById("response").classList.remove("text-danger");
                document.getElementById("response").classList.add("text-success");
            }
            document.getElementById("response").innerHTML = response["message"];
        }
    };
    var params = "username=" + document.getElementById("username").innerHTML + "&oldPassword=" + document.getElementById("oldPassword").value + "&newPassword=" + document.getElementById("newPassword").value;
    xmlhttp.open("POST", "../API/v1/API.php?apicall=changePassword", true);
    xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlhttp.send(params);
}
function deleteAccount()
{
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            response = JSON.parse(xmlhttp.response);
            if (response["error"])
            {
                document.getElementById("response").classList.remove("text-success");
                document.getElementById("response").classList.add("text-danger");
                document.getElementById("response").innerHTML = response["message"];
            }
            else
            {
                window.location.href = '.';
            }
        }
    };
    var params = "username=" + document.getElementById("username").innerHTML + "&password=" + document.getElementById("oldPassword").value;
    xmlhttp.open("POST", "../API/v1/API.php?apicall=deleteUser", true);
    xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlhttp.send(params);
}