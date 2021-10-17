        /************* my name is jeff ☺ and my full name is THN ***********/

var htmldoc; //phòng trường hợp lz DOM lại quăng thêm lỗi dit me may DOM ạ html của m vô duyên như chó rách
//Well i just learned jquery for like 3 days that's why the code may look stupid
function SendData()
{
    var start = new Date().getTime();
    if(document.getElementById("searchtext").value === "") return;
    $("#reloading").empty();
    $("#reloading").append("<div class='loader'></div>");
    htmldoc = null;
    $.ajax({
        url: "searchFilt",
            type: "get", //send it through get method
            data: 
            { 
            txtSearch: document.getElementById("searchtext").value
            },
            success: function(text)
            {
                //100tr bỏ ra cho FPT rồi thì đến lúc cũng phải gặt hái trí tuệ. Những dòng code ko copy paste
                $("#reloading").empty();
                var parser = new DOMParser();
                htmldoc = parser.parseFromString(text,"text/html");
                $("#reloading").html(htmldoc.getElementById("freshair"));
                var end = new Date().getTime();
                var time = end - start;
                console.log("Loaded time: " + time.toString());
            },
            error: function()
            {
                //Do Something to handle error
                // now i know what to do
                $("#reloading").empty();
                $("#reloading").append("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
                console.log("oi dit me cuoc doi");
            }
        });
}
//this one will carry the duty after the user try to violent data after search. God damn i hate them so much.
function kingcrimson(kytu)
{
    //Kono diavolo da (not wibu by da wei)
    var action = kytu.substring(0, 1);
    var so = kytu.substring(1); //lấy số;
    var search = document.getElementById("searchtext").value; //lấy giá trị search
    console.log(action === "u");
    if(action === "u")
    {
        var email = document.getElementById("e" + so).value; //lấy i meo
        var select = document.getElementById(so).value; //lấy value của txtList
        //ditcon me đến cả việc append cái form rồi submit cũng tự đóng tag THẰNG LZ DOMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
        $('<form action="' + 'userAction">' + 
                '<input type="text" name="searchAction" value="updating' + '%' + email + '%' + search + '%' + select + '"' + '/>' + //tại sao vậy DOM?
                                                    '</form>').appendTo('body').submit();
    }
    if(action === "b")
    {
        var email = document.getElementById("e" + so).value; //lấy i meo
        $('<form action="' + 'userAction">' + 
                '<input type="text" name="searchAction" value="banning' + '%' + email + '%' + search + '"' + '/>' + //mày có thể bớt vô duyên đóng tag tự động dc ko
                                                    '</form>').appendTo('body').submit();
    }
    if(action === "a")
    {
        var email = document.getElementById("e" + so).value; //lấy i meo
        $('<form action="' + 'userAction">' + 
                '<input type="text" name="searchAction" value="unbaning' + '%' + email + '%' + search + '"' + '/>' + //mày có thể bớt vô duyên đóng tag tự động dc ko
                                                    '</form>').appendTo('body').submit();
    }
}

//for the dropdown filter
$('.dropdown_filt').click(function ()
{
    $(this).attr('tabindex', 1).focus();
    $(this).toggleClass('active');
    $(this).find('.dropdown-menu').slideToggle(300);
});
$('.dropdown_filt').focusout(function ()
{
    $(this).removeClass('active');
    $(this).find('.dropdown-menu').slideUp(300);
});
$('.dropdown_filt .dropdown-menu li').click(function ()
{
    $(this).parents('.dropdown_filt').find('span').text($(this).text());
    $(this).parents('.dropdown_filt').find('input').attr('value', $(this).attr('id'));
});

function applyButton()
{
    var start = new Date().getTime();
    $("#zawarudo").attr("disabled", "disabled");
    $("#zawarudo").addClass("button_filt_disable");
    setTimeout(function ()
    {
        $("#zawarudo").removeAttr("disabled");
        $("#zawarudo").removeClass("button_filt_disable");
    }, 3000); //za warudo 3s
    //3 value
    let searchtext = document.getElementById("searchtext").value;
    let selgen = document.getElementById("selectedgender").value;
    let selstt = document.getElementById("selectedstatus").value;
    let selr = document.getElementById("selectedrole").value;
    let selm = document.getElementById("selectedmajor").value;
    //checking condition before send to server
    if (selgen === "" && selstt === "" && selr === "" && selm === "")
        return; //bám search ko bấm bấm apply làm cc gì
    //convert to full rolename
    switch(selr)
    {
        case "S": selr = "Student"; break;
        case "A": selr = "Admin"; break;
        case "M": selr = "Mentor"; break;
        default: selr = "all";
    }
    if (selgen !== "" || selstt !== "" || selr !== "" || selm !== "") 
    //đã select 1 cái nhưng những thứ khác để trống
    {
        if(selgen === "") selgen = "all";
        if(selstt === "") selstt = "all";
        if(selr === "") selr = "all";
        if(selm === "") selm = "all";
    }
    console.log("all parameters: " + selgen + " _ " + selstt + " _ " + selr + " _ " + selm);
    //starting the sending
    $("#reloading").empty();
    $("#reloading").append("<div class='loader'></div>");
    htmldoc = null;
    $.ajax({
        url: "searchFilt",
        type: "get", //send it through get method
        data:
                {
                    selectedGender: selgen,
                    selectedStatus: selstt,
                    selectedRole: selr,
                    selectedMajor: selm,
                    txtSearch: searchtext
                },
        success: function (text)
        {
            //response code is here
            $("#reloading").empty();
            var parser = new DOMParser();
            htmldoc = parser.parseFromString(text, "text/html");
            $("#reloading").html(htmldoc.getElementById("freshair"));
            var end = new Date().getTime();
            var time = end - start;
            console.log("Loaded time: " + time.toString());
        },
        error: function ()
        {
            //Old stuff go here
            // now i know what to do
            $("#reloading").empty();
            $("#reloading").append("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
            console.log("oi dit me cuoc doi");
        }
    });
}