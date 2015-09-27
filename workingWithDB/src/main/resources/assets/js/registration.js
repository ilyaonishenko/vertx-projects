/**
 * Created by woqpw on 23.09.15.
 */
function sendDataToServer(){
    console.log("up here");
    var username = document.getElementById("username").value;
    var userpass = document.getElementById("userpassword").value;
    var useruuid = guid();
    var jsonUser = JSON.stringify({"name":username,"password":userpass});
    console.log(jsonUser);
    console.log("username "+username+" password "+userpass+" uuid "+useruuid);
    $.ajax({
        method: "POST",
        url:"/api/user/newuser/"+username,
        data: JSON.stringify({"name":username,"password":userpass,"uuid":useruuid})
        //data:jsonUser
    }).done(function(){
        console.log("done");
    });
}
function echo(){
    $.ajax({
        method:"GET",
        url:"/api/echo",
        data: JSON.stringify({name:"endless"})
    }).done(function(data){
        console.log(data);
    })
}
    function guid() {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    }