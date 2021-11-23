/************* my name is jeff ☺ and my full name is THN ***********/

var htmldoc; //in case DOM bị nunglz
//Well i just learned jquery for like 3 days that's why the code may look stupid
function SendData() {
  var searchtext = document.getElementById("searchtext").value;
  if (searchtext === "") return;
  $("#reloading").empty();
  $("#reloading").html("<div class='loader'></div>");
  htmldoc = null;
  //THIS IS VALIDATING INFORMATION
  let selgen = document.getElementById("selectedgender").value;
  let selstt = document.getElementById("selectedstatus").value;
  let selr = document.getElementById("selectedrole").value;
  let selm = document.getElementById("selectedmajor").value;
  //checking condition before send to server
  //if (selgen === "" && selstt === "" && selr === "" && selm === "") return; //bám search ko bấm bấm apply làm cc gì
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
  $.ajax({
    url: "searchFilt",
    type: "get", //send it through get method
    data: {
      txtSearch: searchtext,
      selectedGender: selgen,
      selectedStatus: selstt,
      selectedRole: selr,
      selectedMajor: selm,
    },
    success: function (text) {
      //100tr bỏ ra cho FPT rồi thì đến lúc cũng phải gặt hái trí tuệ. Những dòng code ko copy paste
      $("#reloading").empty();
      var parser = new DOMParser();
      htmldoc = parser.parseFromString(text, "text/html");
      DidYouDoIt = true;
      $("#reloading").html(htmldoc.getElementById("freshair"));
    },
    error: function () {
      //Do Something to handle error
      // now i know what to do
      $("#reloading").empty();
      $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
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
  let selgen = document.getElementById("selectedgender").value;
  let selstt = document.getElementById("selectedstatus").value;
  let selr = document.getElementById("selectedrole").value;
  let selm = document.getElementById("selectedmajor").value;
  //checking condition before send to server
  if (selgen === "" && selstt === "" && selr === "" && selm === "") return; //check if there are no selection we skip
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
  //starting the sending
  $("#reloading").empty();
  $("#reloading").html("<div class='loader'></div>");
  htmldoc = null;
  $.ajax({
    url: "searchFilt",
    type: "get", //send it through get method
    data: {
      selectedGender: selgen,
      selectedStatus: selstt,
      selectedRole: selr,
      selectedMajor: selm,
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
      $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
      console.log("oi dit me cuoc doi");
    },
  });
}

//this one will carry the duty after the user try to violent data after search.
//GLOBAL WARMING GLOBAL VARIABLE GLOBAL SILENT
var WhichForm; //to query the button for the first time and forever
var DidYouDoIt = false; //check first or second after || DOES NOT RELATED TO after filt
var WhichButtonNumber;

function locMem() 
{
    var selectstt = document.getElementById("selectedstatus").value;
    var selectgen = document.getElementById("selectedgender").value;
    var selectrl = document.getElementById("selectedrole").value;
    var selectm = document.getElementById("selectedmajor").value;
    var selected;
    if (
      selectstt === "" &&
      selectgen === "" &&
      selectrl === "" &&
      selectm === ""
    ) {
      selected = "all";
    } else {
      switch (selectrl) {
        case "S":
          selectrl = "Student";
          break;
        case "A":
          selectrl = "Admin";
          break;
        case "M":
          selectrl = "Mentor";
          break;
        default:
          selectrl = "all";
      }
      if (selectstt === "") selectstt = "all";
      if (selectgen === "") selectgen = "all";
      if (selectm === "") selectm = "all";
      selected = selectgen + "." + selectstt + "." + selectrl + "." + selectm;
    }
    return selected;
}

/*
function kingcrimson(kytu) 
{
  var action = kytu.substring(0, 1);
  var so = kytu.substring(1); //lấy số;
  var search = document.getElementById("searchtext").value;
  if (search === "") search = "all";
  var selected = locMem();
  WhichButtonNumber = so;
  if (action === "u") {
    //var email = document.getElementById("e" + so).value; //lấy i meo
    var selectrole = document.getElementById(so).value; //lấy value của txtList
    if (selectrole === "M") {
      $("#updatemodal").removeClass("hidden");
      $("#updateoverlay").removeClass("hidden");
    } else {
      var email = document.getElementById("e" + so).value;
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
          "%" +
          selected +
          '"' +
          "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
          "</form>"
      )
        .htmlTo("body")
        .submit();
    }
  }
  if (action === "b") {
    $("#banmodal").removeClass("hidden");
    $("#banoverlay").removeClass("hidden");
  }
  if (action === "a") {
    var email = document.getElementById("e" + so).value; //lấy i meo
    $(
      '<form action="' +
        'userAction" method="POST">' +
        '<input type="text" name="searchAction" value="unbaning' +
        "%" +
        email +
        "%" +
        search +
        "%" +
        selected +
        '"' +
        "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
        "</form>"
    )
      .htmlTo("body")
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
        .htmlTo("body")
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
        .htmlTo("body")
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
        .htmlTo("body")
        .submit();
    }
}
*/
function kingcrimson(kytu) //tôi phải wibu
{
    var action = kytu.substring(0, 1);
    var so = kytu.substring(1); //lấy số;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    WhichButtonNumber = so;
    if (action === "u") 
    {
        //var email = document.getElementById("e" + so).value; //lấy i meo
        var selectrole = document.getElementById(so).value; //lấy value của txtList
        if (selectrole === "M") 
        {
            $("#updatemodal").removeClass("hidden");
            $("#updateoverlay").removeClass("hidden");
        } 
        else 
        {
            var email = document.getElementById("e" + so).value;
            $("#reloading").empty();
            $("#reloading").html("<div class='loader'></div>");
            $.ajax
            ({
                url: "UpdateUserServlet",
                type: "post", //send it through post method
                data: 
                {
                  txtEmail: email,
                  selectedRole: selectrole,
                  txtSearch: search,
                  txtSelection: selected
                },
                success: function (text) 
                {
                  $("#reloading").empty();
                  var parser = new DOMParser();
                  htmldoc = parser.parseFromString(text, "text/html");
                  DidYouDoIt = true;
                  $("#reloading").html(htmldoc.getElementById("freshair"));
                  swal({
                            title: "Updating Successfully!",
                            icon: "success",
                            button: "Ok!",
                        });
                },
                error: function () 
                {
                  //Do Something to handle error
                  // now i know what to do
                  $("#reloading").empty();
                  $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
                  console.log("oi dit me cuoc doi");
                },
            });
        }
    }
    if (action === "b") {
      $("#banmodal").removeClass("hidden");
      $("#banoverlay").removeClass("hidden");
    }
    if (action === "a") 
    {
      var email = document.getElementById("e" + so).value; //lấy i meo
      $("#reloading").empty();
      $("#reloading").html("<div class='loader'></div>");
      $.ajax
          ({
              url: "UnbanUserServlet",
              type: "post", //send it through post method
              data: 
              {
                txtEmail: email,
                txtSearch: search,
                txtSelection: selected
              },
              success: function (text) 
              {
                $("#reloading").empty();
                var parser = new DOMParser();
                htmldoc = parser.parseFromString(text, "text/html");
                DidYouDoIt = true;
                $("#reloading").html(htmldoc.getElementById("freshair"));
                swal({
                          title: "Unbanning Successfully!",
                          icon: "success",
                          button: "Ok!",
                      });
              },
              error: function () 
              {
                //Do Something to handle error
                // now i know what to do
                $("#reloading").empty();
                $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
                console.log("oi dit me cuoc doi");
              },
          });
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
        .htmlTo("body")
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
        .htmlTo("body")
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
        .htmlTo("body")
        .submit();
    }*/
}

/****************************** THE ORIGINAL IS HERE ***************************/
/*
function updateButton(formid) //old version of ucp
{
    //popup
    WhichForm = document.getElementById(formid);
    var selectedRole = WhichForm.elements["txtList"].value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    if (selectedRole === "M") 
    {
      $("#updatemodal").removeClass("hidden");
      $("#updateoverlay").removeClass("hidden");
    } 
    else 
    {
        var email = WhichForm.elements["victimEmail"].value;
        $(
          '<form action="' +
            'userAction" method="POST">' +
            '<input type="text" name="searchAction" method="POST" value="updating' +
            "%" +
            email +
            "%" +
            search +
            "%" +
            selectedRole + //one and only for not violating the system
            "%" +
            selected +
            '"' +
            "/>" + //tại sao vậy DOM?
            "</form>"
        )
          .htmlTo("body")
          .submit();
    }
}
*/
function updateButton(formid) 
{
    //popup
    WhichForm = document.getElementById(formid);
    var selectedRole = WhichForm.elements["txtList"].value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    if (selectedRole === "M") 
    {
        $("#updatemodal").removeClass("hidden");
        $("#updateoverlay").removeClass("hidden");
    } 
    else 
    {
        $("#reloading").empty();
        $("#reloading").html("<div class='loader'></div>");
        var email = WhichForm.elements["victimEmail"].value;
        var selected = locMem();
        $.ajax
        ({
            url: "UpdateUserServlet",
            type: "post", //send it through post method
            data: 
            {
              txtEmail: email,
              selectedRole: selectedRole,
              txtSearch: search,
              txtSelection: selected
            },
            success: function (text) 
            {
              $("#reloading").empty();
              var parser = new DOMParser();
              htmldoc = parser.parseFromString(text, "text/html");
              DidYouDoIt = true;
              $("#reloading").html(htmldoc.getElementById("freshair"));
              swal({
                        title: "Updating Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
            },
            error: function () 
            {
              //Do Something to handle error
              // now i know what to do
              $("#reloading").empty();
              $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
              console.log("Debugging log");
            },
        });
    }
}

                                                
function banButton(formid) {
  //popup
  $("#banmodal").removeClass("hidden");
  $("#banoverlay").removeClass("hidden");
  WhichForm = document.getElementById(formid);
  console.log(DidYouDoIt);
}

/*
function unbanButton(formid) {
  //only for the first time cumhere and the later too
  //NOT WORKING AFTER USING SEARCH OR FILTER FUNCTION AS I HAVE USING ANOTHER STAND TO HANDLE IT
  const formdata = document.getElementById(formid);
  var email = formdata.elements["victimEmail"].value;
  var search = document.getElementById("searchtext").value;
  if (search === "") search = "all";
  var selected = locMem();
  console.log(search);
  $(
    '<form action="' +
      'userAction" method="POST">' +
      '<input type="text" name="searchAction" value="unbaning' +
      "%" +
      email +
      "%" +
      search +
      "%" +
      selected +
      '"' +
      "/>" + //mày có thể bớt vô duyên đóng tag tự động dc ko
      "</form>"
  )
    .htmlTo("body")
    .submit();
}*/

function unbanButton(formid) 
{
    //only for the first time cumhere and the later too
    //NOT WORKING AFTER USING SEARCH OR FILTER FUNCTION AS I HAVE USING ANOTHER STAND TO HANDLE IT
    const formdata = document.getElementById(formid);
    var email = formdata.elements["victimEmail"].value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    console.log(search);
    $("#reloading").empty();
    $("#reloading").html("<div class='loader'></div>");
    $.ajax
        ({
            url: "UnbanUserServlet",
            type: "post", //send it through post method
            data: 
            {
              txtEmail: email,
              txtSearch: search,
              txtSelection: selected
            },
            success: function (text) 
            {
              $("#reloading").empty();
              var parser = new DOMParser();
              htmldoc = parser.parseFromString(text, "text/html");
              DidYouDoIt = true;
              $("#reloading").html(htmldoc.getElementById("freshair"));
              swal({
                        title: "Unbanning Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
            },
            error: function () 
            {
              //Do Something to handle error
              // now i know what to do
              $("#reloading").empty();
              $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
              console.log("oi dit me cuoc doi");
            },
        });
}

// ---------------------------------------------------------ALL THE NORMAL BUTTON IS DONE WE WON'T USE THOSE IF THE SEARCH OR FILTER HAS CLICKED
/*
function updateForMentor() {
  if (!DidYouDoIt) {
    //no he didn't
    var email = WhichForm.elements["victimEmail"].value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    var categoryID = document.getElementById("select-category").value;
    console.log(email + " + " + search + " + " + categoryID);
    $(
      '<form action="' +
        'userAction" method="POST">' +
        '<input type="text" name="searchAction" method="POST" value="updating' +
        "%" +
        email +
        "%" +
        search +
        "%" +
        "M" + //one and only for not violating the system
        "%" +
        categoryID +
        "%" +
        selected +
        '"' +
        "/>" + //tại sao vậy DOM?
        "</form>"
    )
      .htmlTo("body")
      .submit();
  } //what did it cost
  else {
    var email = document.getElementById("e" + WhichButtonNumber).value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    var categoryID = document.getElementById("select-category").value;
    $(
      '<form action="' +
        'userAction" method="POST">' +
        '<input type="text" name="searchAction" method="POST" value="updating' +
        "%" +
        email +
        "%" +
        search +
        "%" +
        "M" + //one and only for not violating the system
        "%" +
        categoryID +
        "%" +
        selected +
        '"' +
        "/>" + //tại sao vậy DOM?
        "</form>"
    )
      .htmlTo("body")
      .submit();
  }
}
*/
function updateForMentor() {
  $("#updatemodal").addClass("hidden");
  $("#updateoverlay").addClass("hidden");
  if (!DidYouDoIt) {
    //no he didn't
    var email = WhichForm.elements["victimEmail"].value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    var categoryID = document.getElementById("select-category").value;
    $("#reloading").empty();
    $("#reloading").html("<div class='loader'></div>");
    console.log(email + " + " + search + " + " + categoryID);
    $.ajax
        ({
            url: "UpdateUserServlet",
            type: "post", //send it through post method
            data: 
            {
              txtEmail: email,
              selectedRole: "M",
              txtSearch: search,
              txtSelection: selected,
              selectedCategory: categoryID
            },
            success: function (text)
            {
              $("#reloading").empty();
              var parser = new DOMParser();
              htmldoc = parser.parseFromString(text, "text/html");
              DidYouDoIt = true;
              $("#reloading").html(htmldoc.getElementById("freshair"));
              swal({
                        title: "Updating Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
            },
            error: function () 
            {
              //Do Something to handle error
              // now i know what to do
              $("#reloading").empty();
              $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
              console.log("oi dit me cuoc doi");
            },
        });
  } //what did it cost
  else {
    var email = document.getElementById("e" + WhichButtonNumber).value;
    var search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    var selected = locMem();
    var categoryID = document.getElementById("select-category").value;
    $("#reloading").empty();
    $("#reloading").html("<div class='loader'></div>");
    $.ajax
        ({
            url: "UpdateUserServlet",
            type: "post", //send it through post method
            data: 
            {
              txtEmail: email,
              selectedRole: "M",
              txtSearch: search,
              txtSelection: selected,
              selectedCategory: categoryID
            },
            success: function (text) 
            {
              $("#reloading").empty();
              var parser = new DOMParser();
              htmldoc = parser.parseFromString(text, "text/html");
              DidYouDoIt = true;
              $("#reloading").html(htmldoc.getElementById("freshair"));
              swal({
                        title: "Updating Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
            },
            error: function () 
            {
              //Do Something to handle error
              // now i know what to do
              $("#reloading").empty();
              $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
              console.log("oi dit me cuoc doi");
            },
        });
  }
}

/*
function submitBan() {
  if (!DidYouDoIt) {
    let reason = document.getElementById("ban-area").value;
    let email = WhichForm.elements["victimEmail"].value;
    let search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    let selected = locMem();
    if (reason.trim() === "") {
      alert(
        "Just give me a reason, just a little bit's enough\n" +
          "Just a second we're not broken just bent, and we can learn to love again"
      );
      return;
    }
    $(
      '<form action="' +
        'userAction" method="POST">' + //action
        '<input type="text" name="searchAction" value="banning' +
        "%" +
        email +
        "%" +
        search +
        "%" +
        reason +
        "%" +
        selected +
        '"' +
        "/>" + //chỉ 1 input dc insert thêm cái nữa thì tôi đi ngao du tây tạng
        "</form>"
    )
      .htmlTo("body")
      .submit();
  } else {
    let reason = document.getElementById("ban-area").value;
    let email = document.getElementById("e" + WhichButtonNumber).value;
    let search = document.getElementById("searchtext").value;
    if (search === "") search = "all";
    let selected = locMem();
    console.log(reason + " _ " + email + " _ " + search);
    if (reason.trim() === "") {
      alert(
        "Just give me a reason, just a little bit's enough\n" +
          "Just a second we're not broken just bent, and we can learn to love again"
      );
      return;
    }
    $(
      '<form action="' +
        'userAction" method="POST">' + //action
        '<input type="text" name="searchAction" value="banning' +
        "%" +
        email +
        "%" +
        search +
        "%" +
        reason +
        "%" +
        selected +
        '"' +
        "/>" + //chỉ 1 input dc insert thêm cái nữa thì tôi đi ngao du tây tạng
        "</form>"
    )
      .htmlTo("body")
      .submit();
    //console.log("Are you here");
  }
}*/

function submitBan() 
{
    $("#banmodal").addClass("hidden");
    $("#banoverlay").addClass("hidden");
    if (!DidYouDoIt) 
    {
      let reason = document.getElementById("ban-area").value;
      let email = WhichForm.elements["victimEmail"].value;
      let search = document.getElementById("searchtext").value;
      if (search === "") search = "all";
      let selected = locMem();
      if (reason.trim() === "")
      {
        alert(
          "Just give me a reason, just a little bit's enough\n" +
            "Just a second we're not broken just bent, and we can learn to love again"
        );
        return;
      }
      //Coldplay feat BTS - My universe
      $("#reloading").empty();
      $("#reloading").html("<div class='loader'></div>");
      $.ajax
        ({
            url: "BanUserServlet",
            type: "post", //send it through post method
            data: 
            {
              txtEmail: email,
              txtReason: reason,
              txtSearch: search,
              txtSelection: selected
            },
            success: function (text) 
            {
              $("#reloading").empty();
              var parser = new DOMParser();
              htmldoc = parser.parseFromString(text, "text/html");
              DidYouDoIt = true;
              $("#reloading").html(htmldoc.getElementById("freshair"));
              swal({
                        title: "Banning Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
            },
            error: function () 
            {
              //Do Something to handle error
              // now i know what to do
              $("#reloading").empty();
              $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu</h1>");
              console.log("oi dit me cuoc doi");
            },
        });
    } 
    else 
    {
      let reason = document.getElementById("ban-area").value;
      let email = document.getElementById("e" + WhichButtonNumber).value;
      let search = document.getElementById("searchtext").value;
      if (search === "") search = "all";
      let selected = locMem();
      console.log(reason + " _ " + email + " _ " + search);
      if (reason.trim() === "") {
        alert(
          "Just give me a reason, just a little bit's enough\n" +
            "Just a second we're not broken just bent, and we can learn to love again"
        );
        return;
      }
      //Coldplay feat BTS - My universe
      $("#reloading").empty();
      $("#reloading").html("<div class='loader'></div>");
      $.ajax
        ({
            url: "BanUserServlet",
            type: "post", //send it through post method
            data: 
            {
              txtEmail: email,
              txtReason: reason,
              txtSearch: search,
              txtSelection: selected
            },
            success: function (text) 
            {
              $("#reloading").empty();
              var parser = new DOMParser();
              htmldoc = parser.parseFromString(text, "text/html");
              DidYouDoIt = true;
              $("#reloading").html(htmldoc.getElementById("freshair"));
              swal({
                        title: "Banning Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
            },
            error: function ()
            {
              //Do Something to handle error
              // now i know what to do
              $("#reloading").empty();
              $("#reloading").html("<h1>lỗi òi ko lấy dc gửi được dữ liệu demo failed hmuhmu</h1>");
              console.log("oi dit me cuoc doi");
            },
        });
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
const cancelUpdateBtn = document.querySelector(".cancel-btn-updateRole-modal"); 
const cancelBanBtn = document.querySelector(".cancel-btn-ban-modal"); 
//muốn hiện cái popup khi nhấn nút thì addEventListener rồi thêm 2 dòng code ở dưới
//ban-modal(nếu là update thì chỗ này là updateRoleModal).classList.toggle("hidden");
//banOverlay(nếu là update thì chỗ này là updateOverlay).classList.toggle("hidden");

const submitBanBtn = document.querySelector(".ban-btn-modal");
const banReason = document.querySelector("#ban-area");

cancelUpdateBtn.addEventListener("click",()=>{
    updateOverlay.classList.toggle("hidden");
    updateRoleModal.classList.toggle("hidden");
});

cancelBanBtn.addEventListener("click",()=>{
    banOverlay.classList.toggle("hidden");
    banModal.classList.toggle("hidden");
});

banOverlay.addEventListener("click",()=>{
    banOverlay.classList.toggle("hidden");
    banModal.classList.toggle("hidden");
});

updateOverlay.addEventListener("click",()=>{
    updateOverlay.classList.toggle("hidden");
    updateRoleModal.classList.toggle("hidden");
});

function checkWordCount() {
  if (banReason.value == "") {
    disableSubmitBtn();
  } else {
    submitBanBtn.disabled = false;
    submitBanBtn.style.backgroundColor = "#dc1818";
  }
}

const disableSubmitBtn = () => {
  submitBanBtn.style.backgroundColor = "#f78282";
  submitBanBtn.disabled = true;
};

disableSubmitBtn();
banReason.addEventListener("keyup", () => {
  checkWordCount();
});



//var cmpdm = (function() {
//    var executed = false;
//    return function() {
//        if (!executed) {
//            executed = true;
//             banSuccessfullyModal.classList.toggle("hidden");
//             banSuccessfullyOverlay.classList.toggle("hidden");
//        }
//    };
//})();
//cmpdm();




