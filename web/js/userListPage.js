/************* my name is jeff ☺ and my full name is THN ***********/

var htmldoc; //phòng trường hợp lz DOM lại quăng thêm lỗi dit me may DOM ạ html của m vô duyên như chó rách
//Well i just learned jquery for like 3 days that's why the code may look stupid
function SendData() {
  var start = new Date().getTime();
  if (document.getElementById("searchtext").value === "") return;
  $("#reloading").empty();
  $("#reloading").append("<div class='loader'></div>");
  htmldoc = null;
  $.ajax({
    url: "searchFilt",
    type: "get", //send it through get method
    data: {
      txtSearch: document.getElementById("searchtext").value,
    },
    success: function (text) {
      //100tr bỏ ra cho FPT rồi thì đến lúc cũng phải gặt hái trí tuệ. Những dòng code ko copy paste
      $("#reloading").empty();
      var parser = new DOMParser();
      htmldoc = parser.parseFromString(text, "text/html");
      DidYouDoIt = true;
      $("#reloading").html(htmldoc.getElementById("freshair"));
      var end = new Date().getTime();
      var time = end - start;
      console.log("Loaded time: " + time.toString());
    },
    error: function () {
      //Do Something to handle error
      // now i know what to do
      $("#reloading").empty();
      $("#reloading").append("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
      console.log("oi dit me cuoc doi");
    },
  });
}

function applyButton() {
  var start = new Date().getTime();
  $("#zawarudo").attr("disabled", "disabled");
  $("#zawarudo").addClass("button_filt_disable");
  setTimeout(function () {
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
  if (selgen === "" && selstt === "" && selr === "" && selm === "") return; //bám search ko bấm bấm apply làm cc gì
  //convert to full rolename
  switch (selr) {
    case "S":
      selr = "Student";
      break;
    case "A":
      selr = "Admin";
      break;
    case "M":
      selr = "Mentor";
      break;
    default:
      selr = "all";
  }
  if (selgen !== "" || selstt !== "" || selr !== "" || selm !== "") {
    //đã select 1 cái nhưng những thứ khác để trống
    if (selgen === "") selgen = "all";
    if (selstt === "") selstt = "all";
    if (selr === "") selr = "all";
    if (selm === "") selm = "all";
  }
  console.log(
    "all parameters: " + selgen + " _ " + selstt + " _ " + selr + " _ " + selm
  );
  //starting the sending
  $("#reloading").empty();
  $("#reloading").append("<div class='loader'></div>");
  htmldoc = null;
  $.ajax({
    url: "searchFilt",
    type: "get", //send it through get method
    data: {
      selectedGender: selgen,
      selectedStatus: selstt,
      selectedRole: selr,
      selectedMajor: selm,
      txtSearch: searchtext,
    },
    success: function (text) {
      //response code is here
      $("#reloading").empty();
      var parser = new DOMParser();
      htmldoc = parser.parseFromString(text, "text/html");
      DidYouDoIt = true;
      $("#reloading").html(htmldoc.getElementById("freshair"));
      var end = new Date().getTime();
      var time = end - start;
      console.log("Loaded time: " + time.toString());
    },
    error: function () {
      //Old stuff go here
      // now i know what to do
      $("#reloading").empty();
      $("#reloading").append("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
      console.log("oi dit me cuoc doi");
    },
  });
}

//this one will carry the duty after the user try to violent data after search. God damn i hate them so much.
//GLOBAL WARMING GLOBAL VARIABLE GLOBAL SILENT
var WhichForm; //to query the button for the first time and forever
var DidYouDoIt = false; //check first or second after
var WhichButtonNumber;


function kingcrimson(kytu) 
{
    var action = kytu.substring(0, 1);
    var so = kytu.substring(1); //lấy số;
    var search = document.getElementById("searchtext").value; //lấy giá trị search
    WhichButtonNumber = so;
    if (action === "u") 
    {
        //var email = document.getElementById("e" + so).value; //lấy i meo
        var selectrole = document.getElementById(so).value; //lấy value của txtList
        if(selectrole === "M")
        {
          $("#updatemodal").removeClass("hidden");
          $("#updateoverlay").removeClass("hidden");
        }
        else
        {
          var email = document.getElementById("e" + so);
          $(
            '<form action="' +
            'userAction" method="POST">' +
            '<input type="text" name="searchAction" value="updating' +
            "%" +
            email +
            "%" +
            search +
            "%" +
            selectrole +
            '"' +
            "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
            "</form>"
            )
            .appendTo("body")
            .submit();
        }
    }
    if (action === "b") 
    {
        $("#banmodal").removeClass("hidden");
        $("#banoverlay").removeClass("hidden");
    }
    if (action === "a") 
    {
        var email = document.getElementById("e" + so).value; //lấy i meo
        $(
          '<form action="' +
            'userAction" method="POST">' +
            '<input type="text" name="searchAction" value="unbaning' +
            "%" +
            email +
            "%" +
            search +
            '"' +
            "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
            "</form>"
        )
          .appendTo("body")
          .submit();
    }
    console.log(WhichButtonNumber);
    console.log(DidYouDoIt);
    /* old code ko muốn xóa huhu 
    //Kono diavolo da (not wibu by da wei)
    var action = kytu.substring(0, 1);
    var so = kytu.substring(1); //lấy số;
    var search = document.getElementById("searchtext").value; //lấy giá trị search
    console.log(action === "u");
    if (action === "u") {
      var email = document.getElementById("e" + so).value; //lấy i meo
      var select = document.getElementById(so).value; //lấy value của txtList
      //ditcon me đến cả việc append cái form rồi submit cũng tự đóng tag THẰNG LZ DOMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
      $(
        '<form action="' +
          'userAction">' +
          '<input type="text" name="searchAction" value="updating' +
          "%" +
          email +
          "%" +
          search +
          "%" +
          select +
          '"' +
          "/>" + //tại sao vậy DOM?
          "</form>"
      )
        .appendTo("body")
        .submit();
    }
    if (action === "b") {
      var email = document.getElementById("e" + so).value; //lấy i meo
      $(
        '<form action="' +
          'userAction">' +
          '<input type="text" name="searchAction" value="banning' +
          "%" +
          email +
          "%" +
          search +
          '"' +
          "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
          "</form>"
      )
        .appendTo("body")
        .submit();
    }
    if (action === "a") {
      var email = document.getElementById("e" + so).value; //lấy i meo
      $(
        '<form action="' +
          'userAction">' +
          '<input type="text" name="searchAction" value="unbaning' +
          "%" +
          email +
          "%" +
          search +
          '"' +
          "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
          "</form>"
      )
        .appendTo("body")
        .submit();
    }*/
}

/****************************** THE ORIGINAL IS HERE ***************************/

function updateButton(formid)
{
    //popup
    WhichForm = document.getElementById(formid);
    var selectedRole = WhichForm.elements["txtList"].value;
    var search = "all";
    console.log(selectedRole);
    if(selectedRole === "M")
    {
        $("#updatemodal").removeClass("hidden");
        $("#updateoverlay").removeClass("hidden");
    }
    else
    {
        var email = WhichForm.elements["victimEmail"].value;
        $('<form action="' +
        'userAction">' +
        '<input type="text" name="searchAction" method="POST" value="updating' +
        "%" +
        email +
        "%" +
        search +
        "%" +
        selectedRole + //one and only for not violating the system
        '"' +
        "/>" + //tại sao vậy DOM?
        "</form>"
        )
       .appendTo("body")
       .submit();
    }
}
function banButton(formid)
{
    //popup
    $("#banmodal").removeClass("hidden");
    $("#banoverlay").removeClass("hidden");
    WhichForm = document.getElementById(formid);
    console.log(DidYouDoIt);
}
function unbanButton(formid) //only for the first time cumhere and the later too
{//NOT WORKING AFTER USING SEARCH OR FILTER FUNCTION AS I HAVE USING ANOTHER STAND TO HANDLE IT
    const formdata = document.getElementById(formid);
    var email = formdata.elements["victimEmail"].value;
    var search = document.getElementById("searchtext").value;
    if(search === "") search = "all";
    console.log(search);
    /*
    $('<form action="' + 'userAction" method="POST">' +    //action
            '<input type="text" name="searchAction" value="unbaning' +
      "%" +
      email +
      "%" +
      search +
      '"' +
      "/>" + //chỉ 1 input dc insert thêm cái nữa thì tôi đi ngao du tây tạng
      "</form>"
    )
    .appendTo("body")
    .submit();
    */
}

// ---------------------------------------------------------ALL THE NORMAL BUTTON IS DONE WE WON'T USE THOSE IF THE SEARCH OR FILTER HAS CLICKED
function updateForMentor()
{
    if(!DidYouDoIt) //no he didn't
    {
        var email = WhichForm.elements["victimEmail"].value;
        var search = document.getElementById("searchtext").value;
        if(search === "") search = "all";
        var categoryID = document.getElementById("select-category").value;
        console.log(email + " + " + search + " + " + categoryID);
        $('<form action="' +
            'userAction">' +
            '<input type="text" name="searchAction" method="POST" value="updating' +
            "%" +
            email +
            "%" +
            search +
            "%" +
            "M" + //one and only for not violating the system
            "%" +
            categoryID +
            '"' +
            "/>" + //tại sao vậy DOM?
            "</form>"
         )
        .appendTo("body")
        .submit();
    }
    else //what did it cost
    {
        var email = document.getElementById("e" + WhichButtonNumber);
        var search = document.getElementById("searchtext").value;
        var categoryID = document.getElementById("select-category").value;
        $('<form action="' +
            'userAction">' +
            '<input type="text" name="searchAction" method="POST" value="updating' +
            "%" +
            email +
            "%" +
            search +
            "%" +
            "M" + //one and only for not violating the system
            "%" +
            categoryID +
            '"' +
            "/>" + //tại sao vậy DOM?
            "</form>"
         )
        .appendTo("body")
        .submit();
    }
    
}
function submitBan()
{
    if(!DidYouDoIt)
    {
        let reason = document.getElementById("ban-area").value;
        let email = WhichForm.elements["victimEmail"].value;
        let search = document.getElementById("searchtext").value;
        if(search === "") search = "all";
        console.log(reason + " _ " + email + " _ " + search);
        if(reason.trim() === "")
        {
            alert("Just give me a reason, just a little bit's enough\n" +
                    "Just a second we're not broken just bent, and we can learn to love again");
            return;
        }
        $('<form action="' + 'userAction" method="POST">' +    //action
                '<input type="text" name="searchAction" value="banning' +
          "%" +
          email +
          "%" +
          search +
          "%" +
          reason +
          '"' +
          "/>" + //chỉ 1 input dc insert thêm cái nữa thì tôi đi ngao du tây tạng
          "</form>"
        )
        .appendTo("body")
        .submit();
    }
    else
    {

        let reason = document.getElementById("ban-area").value;
        let email = document.getElementById("e" + WhichButtonNumber).value;
        let search = document.getElementById("searchtext").value;
        //console.log(reason + " _ " + email + " _ " + search);
        if(reason.trim() === "")
        {
            alert("Just give me a reason, just a little bit's enough\n" +
                    "Just a second we're not broken just bent, and we can learn to love again");
            return;
        }
        $('<form action="' + 'userAction" method="POST">' +    //action
                '<input type="text" name="searchAction" value="banning' +
          "%" +
          email +
          "%" +
          search +
          "%" +
          reason +
          '"' +
          "/>" + //chỉ 1 input dc insert thêm cái nữa thì tôi đi ngao du tây tạng
          "</form>"
        )
        .appendTo("body")
        .submit();
        //console.log("Are you here");
    }
}
/**************************** END OF ORIGINAL ***********************/



//for the dropdown filter
$(".dropdown_filt").click(function () {
  $(this).attr("tabindex", 1).focus();
  $(this).toggleClass("active");
  $(this).find(".dropdown-menu").slideToggle(300);
});
$(".dropdown_filt").focusout(function () {
  $(this).removeClass("active");
  $(this).find(".dropdown-menu").slideUp(300);
});
$(".dropdown_filt .dropdown-menu li").click(function () {
  $(this).parents(".dropdown_filt").find("span").text($(this).text());
  $(this)
    .parents(".dropdown_filt")
    .find("input")
    .attr("value", $(this).attr("id"));
});

const banBtns = document.querySelectorAll(".ban-btn");
const banModal = document.querySelector(".ban-modal");
const updateRoleModal = document.querySelector(".updateRole-modal");
const banOverlay = document.querySelector(".ban-overlay");
const updateOverlay = document.querySelector(".updateRole-overlay");

//muốn hiện cái popup khi nhấn nút thì addEventListener rồi thêm 2 dòng code ở dưới
//ban-modal(nếu là update thì chỗ này là updateRoleModal).classList.toggle("hidden");
//banOverlay(nếu là update thì chỗ này là updateOverlay).classList.toggle("hidden");

//nhấn nút cancel thì tắt popup
document
  .querySelector(".cancel-btn-ban-modal")
  .addEventListener("click", () => {
    banModal.classList.toggle("hidden");
    banOverlay.classList.toggle("hidden");
  });

//nhấn nút cancel thì tắt popup
document
  .querySelector(".cancel-btn-updateRole-modal")
  .addEventListener("click", () => {
    updateRoleModal.classList.toggle("hidden");
    updateOverlay.classList.toggle("hidden");
  });

//nhấn ra ngoài cái popu thì tắt cái popup
updateOverlay.addEventListener("click", () => {
  updateRoleModal.classList.toggle("hidden");
  updateOverlay.classList.toggle("hidden");
});

banOverlay.addEventListener("click", () => {
  banModal.classList.toggle("hidden");
  banOverlay.classList.toggle("hidden");
});