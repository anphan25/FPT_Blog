<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="swp.post.PostDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 


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
            src="https://kit.fontawesome.com/1b1fb57155.js"
            crossorigin="anonymous"
        ></script>
        <link rel="stylesheet" href="./styles/contentPageStyle.css" />
        <title>Content | FPT Blog</title>
    </head>
    <body>
        <!-- header  -->
        <!-- header  -->
        <!-- header  -->
        <c:set var="loginStatus" value="${sessionScope.LOGIN}" />
        <c:set var="currentUser" value="${sessionScope.CURRENT_USER}" />
        <c:set var="postDetail" value="${requestScope.POST_DETAIL}" />
        <c:set var="cmtList" value="${requestScope.POST_CMT}"/>
        <!-- header  -->
        <!-- header  -->
        <!-- header  -->
        <header <c:if test="${loginStatus == 'logined'}">login-kind="logined"</c:if><c:if test="${loginStatus != 'logined'}">login-kind="no-login"</c:if>>
                <div class="container_header">
                    <div class="container_left">
                        <div class="toggle_sidebar" onclick="toggleSidebarPhone()">
                            <img src="./images/toggle_sidebar_icon.svg" />
                        </div>
                        <div class="container_logo">
                            <a href="loadBlogs">
                                <img
                                    src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
                                    />
                            </a>
                        </div>
                        <div class="dropdown_category">
                            <div class="container_category">
                                <p>Categories</p>
                            </div>
                            <div class="dropdown_category_content">
                            <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}">
                                <div class="dropdown_category_item">
                                    <c:url var="cateLink" value="searchByCategory">
                                        <c:param name="categoryId" value="${cateDTO.ID}" />
                                    </c:url>
                                    <a href="${cateLink}">${cateDTO.name}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container_searchBar">
                        <form id="searchit" action="searchTitle">
                            <input
                                placeholder="Search..."
                                name="titleValue"
                                autocomplete="off"
                                />
                            <div class="container_icon" onclick="submit_form()">
                                <i class="fas fa-search"></i>
                            </div>
                        </form>
                    </div>
                </div>
                <c:if test="${loginStatus == 'logined'}">
                    <div class="container_right">
                        <c:if test="${currentUser.role == 'S'}">
                            <div class="container_button_register">
                                <a href="createPostPage"><button>Create Post</button></a>
                            </div>
                        </c:if>
                        <c:if test="${currentUser.role == 'M'}">
                            <div class="container_button_register">
                                <a href="loadPendingPosts?postStatus=WFA"><button>Pending Post</button></a>
                            </div>
                        </c:if>
                        <c:if test="${currentUser.role == 'A'}">
                            <div class="container_button_register">
                                <a href="createCategoryPage"
                                   ><button>Create Category</button></a
                                >
                            </div>
                        </c:if>
                        <a href="messengerPage">
                            <div class="icon_notification_container">
                                <img src="./images/chat.svg" />
                            </div>
                        </a>
                        <div class="dropdown">
                            <div class="dropbtn_noti">
                                <img src="./images/notification_icon.svg" />
                                <div id="warning" class="warning warning-hidden">!</div>
                            </div>
                            <div class="dropdown-content1">
                            </div>
                        </div>

                        <div class="dropdown">
                            <div class="dropbtn">
                                <img src="${currentUser.avatar}" />
                            </div>
                            <div class="dropdown-content">
                                <div class="item-top">
                                    <a
                                        ><h2>${currentUser.name}</h2>
                                        <p>@${currentUser.name}</p></a
                                    >
                                </div>
                                <div style="padding: 0.5rem 0">
                                    <div class="item">
                                        <c:url var="loadCurrentProfileLink" value="loadProfile">
                                            <c:param name="email" value="${currentUser.email}" />
                                        </c:url>
                                        <a href="${loadCurrentProfileLink}"><p>Profile</p></a>
                                    </div>
                                </div>
                                <div class="item-bottom">
                                    <a href="logout">Sign Out</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${loginStatus != 'logined'}">
                    <div class="container_right">
                        <div class="container_button_login">
                            <button><a href="firstLoginPage">Login</a></button>
                        </div>
                        <div class="container_button_register">
                            <button><a href="registerPage">Create account</a></button>
                        </div>
                    </div>
                </c:if>
            </div>
        </header>

        <!-- sidebar_phone -->
        <!-- sidebar_phone -->
        <!-- sidebar_phone -->
        <section class="sidebar_phone" id="sidebar_phone">
            <div class="container_sidebar_phone" id="container_sidebar_phone">
                <div class="container_toggle">
                    <h2 class="title">FPT Blog</h2>
                    <img
                        src="./images/close_button_icon.svg"
                        onclick="handleClickOutside()"
                        />
                </div>
                <div style="padding: 0.5rem">
                    <div class="introduce_community">
                        <h2 class="introduce_title">
                            <span class="brand_text">DEV Community</span> is a community of
                            690,628 amazing developers
                        </h2>
                        <p class="introduce_content">
                            We're a place where coders share, stay up-to-date and grow their
                            careers.
                        </p>
                        <div class="container_button">
                            <div class="container_button_register">
                                <button><a href="/login.html">Táº¡o tÃ i khoáº£n</a></button>
                            </div>
                            <div class="container_button_login">
                                <button><a href="/login.html">ÄÄng nháº­p</a></button>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Menu</h2>
                        <div class="container_item">
                            <img src="./images/house_icon.svg" />
                            <p>Trang chá»§</p>
                        </div>
                        <div class="container_item">
                            <img src="./images/hand_shake_icon.svg" />
                            <p>ÄÄng nháº­p</p>
                        </div>
                    </div>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Tags phá» biáº¿n</h2>
                        <div class="container_item">
                            <p>#nodejs</p>
                        </div>
                        <div class="container_item">
                            <p>#python</p>
                        </div>
                        <div class="container_item">
                            <p>#devops</p>
                        </div>
                        <div class="container_item">
                            <p>#angular</p>
                        </div>
                        <div class="container_item">
                            <p>#vuejs</p>
                        </div>
                    </div>
                </div>
            </div>
            <div
                class="outside_sidebar_phone"
                id="outside_sidebar_phone"
                onclick="handleClickOutside()"
                ></div>
        </section>

        <!-- container   -->
        <!-- container   -->
        <!-- container   -->
        <div class="container">
            <div class="row">
                <div class="interact">
                    <c:if test="${loginStatus == 'logined'}">
                        <div class="interact-item">
                            <c:set var="likeStatus" value="${requestScope.LIKE_STATUS}"/>
                            <div class="${likeStatus == 'yes' ? "clicked-like-icon" : ""} icon like-icon" onclick="likePost()">
                                <img src="./images/vote_icon.svg" alt="" />
                            </div>
                            <p class="${likeStatus == 'yes' ? "clicked-like-text" : ""} totalLike">${postDetail.likes}</p>
                        </div>

                        <div class="interact-item">
                            <div class="icon cmt-icon">
                                <img src="./images/comment_icon.svg" alt="" />
                            </div>
                            <p class="cmtCount">${postDetail.comments}<p>
                        </div>
                    </c:if>
                    <c:if test="${loginStatus != 'logined'}">
                        <div class="interact-item">
                            <div class="icon like-icon">
                                <img src="./images/vote_icon.svg" alt="" />
                            </div>
                            <p class="totalLike">${postDetail.likes}</p>
                        </div>

                        <div class="interact-item">
                            <div class="icon cmt-icon">
                                <img src="./images/comment_icon.svg" alt="" />
                            </div>
                            <p>${postDetail.comments}<p>
                        </div>
                    </c:if>

                </div>
                <div class="main-content">
                    <c:if test="${currentUser.email == postDetail.emailPost}">
                        <i class="fas fa-ellipsis-h dot-icon"></i>
                        <div class="delete-update-div hidden">

                            <div class="delete-div option-div">
                                <i class="fas fa-trash"></i>
                                <p>Delete</p>
                            </div>

                            <a href="loadOldContent?postId=${postDetail.ID}">
                                <div class="update-div option-div">
                                    <i class="fas fa-edit"></i>
                                    <p>Update</p>
                                </div>
                            </a>
                        </div>
                    </c:if>
                    <div class="main-content-up">
                        <div class="title">
                            <p class="title-text">${postDetail.title}</p>
                            <div class="tag">
                                <c:forEach var="tag" items="${postDetail.tag}">
                                    <c:url var="searchByTagLink" value="searchByTag">
                                        <c:param name="tag" value="${tag}" />
                                    </c:url>
                                    <a href="${searchByTagLink}">
                                        <p><span class="hash"></span>#${tag}</p>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="owner">
                                <div class="avt">
                                    <img class="avt-img" src="${postDetail.avatar}" alt="" />
                                </div>
                                <form action="">
                                    <div class="name-award">
                                        <div class="name">
                                            <a href="loadProfile?email=${postDetail.emailPost}">${postDetail.namePost}</a>
                                        </div>
                                        <p class="user-award">
                                            <c:if test="${not empty postDetail.awards}">
                                                <c:forEach var="award" items="${postDetail.awards}">
                                                    <c:if test="${award == 1}">
                                                        <img src="./images/contribution-award.svg" alt=""/>
                                                    </c:if>
                                                    <c:if test="${award == 2}">
                                                        <img src="./images/heart-award.png" alt=""/>
                                                    </c:if>
                                                    <c:if test="${award == 3}">
                                                        <img src="./images/top-author.png" alt=""/>
                                                    </c:if>

                                                </c:forEach>
                                            </c:if>
                                        </p>
                                    </div>
                                </form>
                                <div class="time">${postDetail.approvedDate}</div>
                            </div>
                        </div>
                        <div class="content-img-text">
                            <c:out value="${postDetail.postContent}" escapeXml="false" />
                        </div>
                    </div>
                    <div class="comment">
                        <p class="comment-title">Discussion (<span class="cmtCount2">${postDetail.comments}</span>)</p>
                        <div class="current-user-comment">
                            <div class="user-comment-item">
                                <textarea
                                    id="textarea-cmt"
                                    cols="30"
                                    rows="10"
                                    placeholder="Add to the discussion"
                                    ></textarea>
                                <input id="postId-input" type="hidden" value="${postDetail.ID}" />
                                <input id="ownerEmail-input" type="hidden" value="${currentUser.email}" />
                                <input id="ownerName-input" type="hidden" value="${currentUser.name}" />
                                <input id="chuBaiVietName-input" type="hidden" value="${postDetail.namePost}" />

                                <button
                                    onclick="loadNewComment()"
                                    class="submit-btn hidden"
                                    disabled="true"
                                    >
                                    Submit
                                </button>

                            </div>
                        </div>

                        <div id="comments-container">
                            <c:if test="${not empty cmtList}">
                                <c:forEach var="listDTO" items="${cmtList}">
                                    <div class="others-comments" id="comment-div-${listDTO.ID}">
                                        <div class="user-avt">
                                            <img
                                                class="avt-img"
                                                src="${listDTO.avatar}"
                                                alt=""
                                                />
                                        </div>
                                        <div class="comment-item" id="comment-item-${listDTO.ID}">
                                            <div class="comment-info" id="comment-info-${listDTO.ID}">
                                                <a href="loadProfile?email=${listDTO.emailComment}">
                                                    <div class="name-award">
                                                        <div class="name">${listDTO.name}</div>
                                                        <p class="user-award">
                                                            <c:if test="${not empty listDTO.awards}">
                                                                <c:forEach var="award" items="${listDTO.awards}">
                                                                    <c:if test="${award == 1}">
                                                                        <img src="./images/contribution-award.svg" alt=""/>
                                                                    </c:if>
                                                                    <c:if test="${award == 2}">
                                                                        <img src="./images/heart-award.png" alt=""/>
                                                                    </c:if>
                                                                    <c:if test="${award == 3}">
                                                                        <img src="./images/top-author.png" alt="">
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </p>
                                                    </div>
                                                </a>
                                                <div class="comment-time">${listDTO.date}</div>
                                            </div>
                                            <div class="comment-content" id="comment-content-${listDTO.ID}">
                                                <p id="comment-content-p-${listDTO.ID}">${listDTO.content}</p>
                                            </div>
                                            <div class="editForm" id="edit-form-${listDTO.ID}">

                                                <textarea
                                                    name="cmt"
                                                    minlength="1"
                                                    class="textarea-cmt-edit"
                                                    id="textarea-cmt-edit-${listDTO.ID}"
                                                    cols="30"
                                                    rows="10"
                                                    placeholder="Edit your comment here..."
                                                    >${listDTO.content}</textarea>
                                                <div class="container-button-edit">
                                                    <div class="container-icon-close" id="${listDTO.ID}">
                                                        <i class="fas fa-times" id="${listDTO.ID}"></i>
                                                    </div>
                                                    <div class="container-icon-edit" id="${listDTO.ID}">
                                                        <i class="fas fa-check" id="${listDTO.ID}"></i>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <c:if test="${currentUser.email == listDTO.emailComment || currentUser.role == 'A'}">                              
                                            <div class="edit-delete" id="edit-delete-${listDTO.ID}">
                                                <c:if test="${currentUser.email == listDTO.emailComment}">
                                                    <button class="editButton" id="${listDTO.ID}"><i class="fas fa-pen"></i>Edit</button>
                                                </c:if>
                                                <c:if test="${currentUser.role == 'A' || currentUser.email == listDTO.emailComment}">
                                                    <button class="deleteButton" id="${listDTO.ID}"><i class="fas fa-trash-alt"></i> Delete</button>
                                                </c:if>
                                            </div>   
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="left-container">
                    <div class="left-container-title">
                        <h3>Common Posts</h3>
                    </div>
                    <c:forEach var="commonPost" items="${requestScope.COMMON_POST}">
                        <a href="loadPostContent?postId=${commonPost.ID}">
                            <div class="left-container-content">
                                <div class="left-container-item">
                                    <h6>${commonPost.title}</h6>
                                    <div class="like-cmt">
                                        <p>${commonPost.likes} Likes</p>
                                        <p>${commonPost.comments} Comments</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>  
        </div>
        <!-- modal -->
        <!-- modal -->
        <!-- modal -->
        <div class="login-modal hidden">
            <div class="login-modal-title">
                <h2>Log in to continue</h2>
                <div class="close-btn-modal-div">
                    <i class="fas fa-times close-modal-btn"></i>
                </div>
            </div>
            <div class="login-modal-content">
                <div class="login-modal-logo">
                    <img
                        src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
                        alt=""
                        />
                </div>
                <div class="login-modal-button">
                    <a class="login-link" href="firstLoginPage">Log in</a>
                    <a class="register-link" href="registerPage">Create new account</a>
                </div>
            </div>
            <div class="login-modal-footer">
                We strive for transparency and don't collect excess data.
            </div>
        </div>
        <div class="delete-modal hidden">
            <h1>Are you sure you want to delete this post ?</h1>
            <div class="del-btn-gr">
                <a href="deletePost?postId=${postDetail.ID}">
                    <button class="del-btn">Delete</button>
                </a>
                <button class="cancel-btn">Cancel</button>
            </div>
        </div>
        <div class="overlay hidden"></div>
        <div class="delete-overlay hidden"></div>
        <!-- footer -->
        <!-- footer -->
        <!-- footer -->
        <footer>
            <div class="container_footer">
                <p>
                    <span class="text_footer_strong">DEV Community</span> - A constructive
                    and inclusive social network for software developers. With you every
                    step of your journey.
                </p>
                <div style="margin: 0.25rem 0"></div>
                <p>
                    Built on <span class="text_footer_strong">Forem</span> - the
                    <span class="text_footer_strong">open source</span> software that
                    powers DEV and other inclusive communities
                </p>
            </div>
            <div class="text_footer_container">
                <p class="text_footer">
                    Made with
                    <i class="fa fa-heart" style="color: rgb(255, 70, 50)"></i> by
                    <span class="text_footer_strong">Ân, An, Đan, Nam, Phương</span> ©
                    2021
                </p>
                <img src="./images/forem_icon.svg" />
            </div>
        </footer>
        <!-- script   -->
        <!-- script   -->
        <!-- script   -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>

        <script>
                                        function handlePopup(idDOM) {
                                            let id = idDOM;
                                            console.log(id);
                                            $(`#comment-info-` + id).toggleClass('toggleDisplay');
                                            $(`#comment-content-` + id).toggleClass('toggleDisplay');
                                            $(`.edit-delete`).toggleClass('toggleDisplay');
                                            $(`#edit-form-` + id).toggleClass('editFormOpen');
                                        }

                                        function handleChangedText(idDom, content) {
                                            let id = idDom;
                                            document.getElementById(`comment-content-p-` + id).textContent = content;
                                        }

                                        $(document).on('click', '.editButton', function (e) {
                                            console.log(e);
                                            handlePopup(e.target.id.length > 0 ? e.target.id : e.currentTarget.id);
                                        });
                                        $(document).on('click', '.container-icon-close', function (e) {
                                            e.preventDefault();
                                            handlePopup(e.target.id.length > 0 ? e.target.id : e.currentTarget.id);
                                        });
                                        $(document).on('click', '.container-icon-edit', async function (e) {
                                            e.preventDefault();
                                            let id = e.target.id.length > 0 ? e.target.id : e.currentTarget.id;
                                            let content = document.querySelector("#textarea-cmt-edit-" + id).value;
                                            if (content.length > 0) {
                                                let oldContent = document.getElementById(`comment-content-p-` + id).textContent;
                                                if (oldContent == content) {
                                                    handlePopup(id);
                                                } else {
                                                    await editComment(id, content);
                                                    handlePopup(id);
                                                }

                                            } else {
                                                alert('Your input is empty! Check again');
                                            }

                                        });

                                        function editComment(id, content) {
                                            $.ajax({
                                                url: "EditCommentServlet",
                                                data: {
                                                    cmtID: id,
                                                    cmtContent: content
                                                },
                                                type: "POST",
                                                success: function (response) {
                                                    handleChangedText(id, content);
                                                },
                                                error: function (xhr) {
                                                    alert('Server internal error.')
                                                }
                                            });
                                        }
                                        ;
        </script>

        <script>
            // Initialize Firebase
            firebase.initializeApp({
                apiKey: 'AIzaSyAPgZZxNDsNeVB-C6hMGKzsFelsBRIjdBI',
                authDomain: 'udemy-vue-firebase-si.firebaseapp.com',
                projectId: 'udemy-vue-firebase-si',
            });
            const db = eval('firebase.firestore()');
            const cmtContent = document.querySelector("#textarea-cmt");
            const postId = document.querySelector("#postId-input");
            const ownerCmtEmail = document.querySelector("#ownerEmail-input");
            const ownerCmtName = document.querySelector("#ownerName-input");
            const chuBaiVietName = document.querySelector("#chuBaiVietName-input");
            const totalLike = document.querySelector(".totalLike");
            const cmtCount = document.querySelector(".cmtCount");
            const cmtCount2 = document.querySelector(".cmtCount2");
            const deleteBtn = document.querySelector(".deleteButton");

            function deleteCommentInUI(id) {
                let deleteCmt = document.querySelector("#comment-div-" + id);
                deleteCmt.remove();
                cmtCount.textContent = Number(cmtCount.textContent) - 1;
                cmtCount2.textContent = Number(cmtCount2.textContent) - 1;
            }

            function deleteComment(id) {
                $.ajax({
                    url: "DeleteCommentServlet",
                    data: {
                        cmtID: id
                    },
                    type: "POST",
                    success: function (response) {
                        deleteCommentInUI(id);
                    },
                    error: function (xhr) {
                        alert('Server internal error.');
                    }
                });
            }
            $(document).on('click', '.deleteButton', function (e) {
                e.preventDefault();
                deleteComment(e.target.id.length > 0 ? e.target.id : e.currentTarget.id);

            });

            const addDocument = (action = "thích") => {
                let ownerID = `${postDetail.emailPost}`;
                ownerID = ownerID.substr(0, ownerID.indexOf("@"));
                let currentUsername = `${currentUser.name}`;
                let userAvatar = `${currentUser.avatar}`;
                db.collection('notify')
                        .doc(ownerID)
                        .collection("incoming")
                        .add({
                            postId: postId.value,
                            ownerId: ownerID,
                            user: currentUsername,
                            avatar: userAvatar,
                            createdAt: new Date(),
                            action: action,
                            seen: false
                        })
                        .catch((error) => {
                            console.error('Error adding document: ', error);
                        });
            }
            ;




            function loadNewComment() {
                $.ajax({
                    url: "CommentServlet",
                    data: {
                        postId: postId.value,
                        ownerCmtEmail: ownerCmtEmail.value,
                        cmtContent: cmtContent.value
                    },
                    type: "POST",
                    success: function (response) {
                        addDocument("bình luận");
                        const newCmt = document.querySelector("#comments-container");
                        newCmt.insertAdjacentHTML('afterbegin', response);
                        cmtCount.textContent = Number(cmtCount.textContent) + 1;
                        cmtCount2.textContent = Number(cmtCount2.textContent) + 1;
                    },
                    error: function (xhr) {
                        console.log("loi me roi");
                    }
                });
            }
            ;

            function likePost() {
                $.ajax({
                    url: "LikePostServlet",
                    data: {
                        postId: postId.value,
                        emailLike: ownerCmtEmail.value,
                    },
                    type: "POST",
                    success: function (response) {
                        if (parseInt(response) > 0) {
                            totalLike.textContent = response;
                            addDocument("thích");
                        } else {
                            totalLike.textContent = parseInt(totalLike.textContent - 1);
                        }
                    },
                    error: function (xhr) {
                        console.log("loi me roi");
                    }
                });
            }
            ;
            function toggleSidebarPhone() {
                const toggle_sidebar = document.getElementById("sidebar_phone");
                toggle_sidebar.style.display = "block";
            }
            function handleClickOutside() {
                const toggle_sidebar = document.getElementById("sidebar_phone");
                toggle_sidebar.style.display = "none";
            }
            function submit_form() {
                var form = document.getElementById("searchit");
                form.submit();
            }
            const dotIcon = document.querySelector(".dot-icon");
            if (dotIcon) {
                const optionDiv = document.querySelector(".delete-update-div");
                dotIcon.addEventListener("click", () => {
                    optionDiv.classList.toggle("hidden");
                });
            }
//  
        </script>
        <script src="./js/contentPage.js"></script>
        <!--     <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
                <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>-->
        <script>
            // Initialize Firebase
//            firebase.initializeApp({
//                apiKey: 'AIzaSyAPgZZxNDsNeVB-C6hMGKzsFelsBRIjdBI',
//                authDomain: 'udemy-vue-firebase-si.firebaseapp.com',
//                projectId: 'udemy-vue-firebase-si',
//            });
            const notiWrapper = document.querySelector(".dropdown-content1");
            let lastestNotiCreatedAt = "";
            let componentRunOnDepend = false;
            let lol= false;
            let currentUser = `${currentUser.email}`;
            currentUser = currentUser.substr(0, currentUser.indexOf("@"));
            const itemNoti = (avatar, user, action, postID, createdAt) => {
                return (
                        ` <a href="loadPostContent?postId=\${postID}">
                            <div class="noti_item">
                                <img class="noti_other_user"  src="\${avatar}"/>
                                  <div>
                                     <p><b>\${user}</b> đã <b>\${action}</b> bài viết của bạn</p>
                                    <p style="font-size: 14px; margin-top: 0.2rem">\${createdAt}</p>
                                  </div>
                            </div>
                        </a>`
                        )
            }
            
             const itemNotiNew = (avatar, user, action, postID, createdAt) => {
                return (
                        ` <a href="loadPostContent?postId=\${postID}">
                            <div class="noti_item_new">
                                <img class="noti_other_user"  src="\${avatar}"/>
                                  <div>
                                     <p><b>\${user}</b> đã <b>\${action}</b> bài viết của bạn</p>
                                    <p style="font-size: 14px; margin-top: 0.2rem">\${createdAt}</p>
                                  </div>
                            </div>
                        </a>`
                        )
            }

            $(".dropbtn_noti").hover(function (e) {
                $("#warning").addClass("warning-hidden");
            });
            // Functions
            const componentDidMount = (function () {
                let ref = false;
                return function () {
                    if (!ref) {
                        ref = true;
                        componentRunOnDepend = true;
                        getDocumentOnMount();
                    }
                };
            })();

            // useEffect
            componentDidMount();

            async function getDocumentOnMount() {
                let domMessage = '';
                let notifyRealtime = [];
                await db
                        .collection('notify')
                        .doc(currentUser)
                        .collection("incoming")
                        .orderBy('createdAt', 'desc')
                        .limit(5)
                        .get()
                        .then((querySnapshot) => {
                            querySnapshot.forEach((doc) => {
                                notifyRealtime.push(doc.data());
                            });
                        })
                        .catch((error) => {
                            console.log('Error getting documents: ', error);
                        });
                if (notifyRealtime.length > 0) {
                    notifyRealtime.forEach((doc, index) => {
                        if (doc.createdAt) {
                            if (index === notifyRealtime.length - 1) {
                                lastestNotiCreatedAt = doc.createdAt.seconds;
                            }
                            var date = new Date(doc.createdAt.toDate()).toLocaleString("en-GB", {year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit'});
                            domMessage += itemNoti(doc.avatar, doc.user, doc.action, doc.postId, date);

                        }
                    });
                } else {
                    domMessage += `<div class="noti_item">
                                        <p></p>
                                    </div>
                                </div>`;
                }
                notiWrapper.innerHTML = domMessage;
            }

            if (componentRunOnDepend) {
                db.collection('notify')
                        .doc(currentUser)
                        .collection("incoming")
                        .orderBy('createdAt', 'desc')
                        .limit(1)
                        .onSnapshot((querySnapshot) => {
                            let domMessage = '';
                            let notifyRealtime = [];
                            querySnapshot.forEach((doc) => {
                                if (doc.exists) {
                                    let id = doc.id;
                                    let data = {...doc.data(), id};
                                    notifyRealtime.push(data);
                                }
                            });
                            notifyRealtime.forEach((doc, index) => {
                                if (doc.createdAt) {
                                    console.log(lastestNotiCreatedAt, doc.createdAt.seconds);
                                    if (lol) {
                                        var date = new Date(doc.createdAt.toDate()).toLocaleString("en-GB", {year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit'});
                                        domMessage += itemNotiNew(doc.avatar, doc.user, doc.action, doc.postId, date);
                                    }
                                    lol = true;
                                }
                            });
                            if (domMessage !== '') {
                                notiWrapper.insertAdjacentHTML('afterbegin', domMessage);
                                $("#warning").removeClass("warning-hidden");
                            }
                        });
                    }


        </script>
    </body>
</html>