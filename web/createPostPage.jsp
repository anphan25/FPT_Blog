<%@page import="swp.account.AccountDTO"%> <%@page contentType="text/html"
pageEncoding="UTF-8"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/03ade0a214.js"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.tiny.cloud/1/v5o2pa741vownqney7esv5efwtt657k8j38w8k2womugtuyg/tinymce/5/tinymce.min.js"
      referrerpolicy="origin"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js"></script>
    <link rel="stylesheet" href="./styles/createPostPage.css" />
    <title>New Post | FPT Blog</title>
  </head>
  <body>
    <% AccountDTO user = (AccountDTO) session.getAttribute("CURRENT_USER"); if
    (user == null) { response.sendRedirect("loadBlogs"); } else { %>

    <header>
      <div class="container_header">
        <div class="container_left">
          <div class="container_logo">
            <a href="loadBlogs">
              <img
                src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
              />
            </a>
          </div>
        </div>

        <div class="container_right">
          <div class="icon_notification_container">
            <img src="./images/close_button_icon.svg" />
          </div>
        </div>
      </div>
    </header>

    <!-- Main -->

    <section class="main">
      <div class="container_main">
        <div class="container_left"></div>
        <div class="container_middle">
          <div class="absolute_edit">
            <p>Edit</p>
          </div>
          <form
            action="createPost"
            method="POST"
            onkeydown="return event.key != 'Enter';"
          >
            <div class="container_content">
              <div class="textarea_title_container">
                <textarea
                  name="title"
                  id="title_textarea"
                  placeholder="New post title here..."
                  maxlength="120"
                  minlength="1"
                  onclick="handleMouse(event)"
                ></textarea>
              </div>
              <div class="textarea_categories_container">
                <label for="cars">What's your post talking about:</label>
                <select name="category" id="category">
                  <c:forEach
                    var="cateDTO"
                    items="${sessionScope.CATEGORY_LIST}"
                  >
                    <option value="${cateDTO.ID}">${cateDTO.name}</option>
                  </c:forEach>
                </select>
              </div>
              <div>
                <input type="hidden" name="tags" id="tags" />
                <input
                  type="hidden"
                  name="email"
                  value="<%= user.getEmail()%>"
                />
              </div>
              <div class="textarea_tags_container" id="textarea_tags_container">
                <input
                  name="tagsS"
                  type="text"
                  id="tagsS_textarea"
                  placeholder="Add up to 4 tags..."
                  onclick="handleMouse(event)"
                  maxlength="60"
                />
              </div>

              <div class="textarea_content_container">
                <textarea id="content_area" name="content">
                    <h3>Hi, bạn có thể copy/paste ảnh upload thoải mái nha</h3>
                                </textarea
                >
              </div>
            </div>
          </form>
          <div class="container_button">
            <button onclick="getContent()">Publish</button>
          </div>
        </div>
        <div class="container_info"></div>
      </div>
    </section>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
      tinymce.init({
        selector: "#content_area",
        setup: function (ed) {
          ed.on("click", function (event) {
            console.log(`Mouse X: ${event.clientX}, Mouse Y: ${event.clientY}`);
            const ref = document.querySelector(".container_info");
            const stringHtml = `<div style="position: sticky; top: ${50}vh" class="pseudo_info"><h3>Editor Basics</h3><li>We use TinyMCE.</li></div>`;
            ref.innerHTML = stringHtml;
          });
        },
        plugins: "autoresize",
        toolbar:
          "formatselect | " +
          "bold italic backcolor forecolor| alignleft aligncenter " +
          "alignright alignjustify | bullist numlist outdent indent | " +
          "removeformat | help | codesample",
        content_style:
          "body { font-weight: 400; font-size:18px }" + "p { margin: 0",
        toolbar_mode: "floating",
        statusbar: false,
      });

      const form = document.querySelector("form");
      form.addEventListener("submit", (event) => {
        event.preventDefault();
      });

      function getContent() {
        let tags = "";
        let tagJquery = $("#textarea_tags_container button");
        console.log(tagJquery);
        let title = document.getElementById("title_textarea").value;
        if (title) {
          if (tagJquery.length == 0 || tagJquery.length > 4) {
            console.log(tagJquery.length == 0);
            if (tagJquery.length == 0) {
              swal(
                "Have some tags for better",
                "Tags help your post a lot in SEO",
                "error",
                {
                  button: "Ok",
                }
              );
            } else {
              swal(
                "Too much tags",
                "Too much tags make your post over control",
                "error",
                {
                  button: "Ok",
                }
              );
            }
            return;
          } else {
            $("#textarea_tags_container button").each(function () {
              let test = $(this)[0].innerHTML;
              tags += test + " ";
            });
            var myContent = tinymce.get("content_area").getContent();
            $("#tags").val(tags);
            form.submit();
          }
        } else {
          swal("You miss your title", "", "error", { button: "Ok" });
          return;
        }
      }
    </script>
    <script>
      $("#textarea_tags_container input").on("keyup", function (e) {
        var key = e.which;
        if (key == 188 || key == 13) {
          if ($(this).val().length === 1 && $(this).val().indexOf(",") !== -1) {
            $(this).val("").focus();
            return;
          }
          $('<button id="tag"/>')
            .text(
              $(this)
                .val()
                .slice(0, key == 188 ? -1 : $(this).val().length)
            )
            .insertBefore($(this));
          $(this).val("").focus();
        }
      });

      $("#textarea_tags_container").on("click", "button", function (e) {
        e.preventDefault();
        $(this).remove();
        return false;
      });
      $("#title_textarea")
        .each(function () {
          this.setAttribute(
            "style",
            "height:" + this.scrollHeight + "px;overflow-y:hidden;"
          );
        })
        .on("input", function () {
          this.style.height = "0";
          this.style.height = this.scrollHeight + "px";
        });

      function handleMouse(event) {
        const ref = document.querySelector(".container_info");
        if (event.srcElement.id === "title_textarea") {
          const stringHtml = `<div style="position: sticky; top: ${event.clientY}px" class="pseudo_info"><h3>Writing a Great Post Title</h3><li>Think of your post title as a super short (but compelling!) description — like an overview of the actual post in one short sentence.</li><li>Use keywords where appropriate to help ensure people can find your post by search.</li></div>`;
          ref.innerHTML = stringHtml;
        } else {
          const stringHtml = `<div style="position: sticky; top: ${event.clientY}px" class="pseudo_info"><h3>Tagging Guidelines</h3><li>Tags help people find your post.</li><li>Think of tags as the topics or categories that best describe your post.</li><li>Add up to four comma-separated tags per post. Combine tags to reach the appropriate subcommunities.</li><li>Use existing tags whenever possible.
    Some tags, such as “help” or “healthydebate”, have special posting guidelines.</li></div>`;
          ref.innerHTML = stringHtml;
        }
      }
    </script>
    <% } %>
  </body>
</html>
