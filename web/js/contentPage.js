const textareaComment = document.querySelector("#textarea-cmt");

const userComment = document.querySelector(".current-user-comment");
const submitBtn = document.querySelector(".submit-btn");
const likeIcon = document.querySelector(".like-icon");
const cmtIcon = document.querySelector(".cmt-icon");
const loginModal = document.querySelector(".login-modal");
const overlay = document.querySelector(".overlay");
const closeBtnModal = document.querySelector(".close-btn-modal-div");
const header = document.querySelector("header");
const userCommentItem = document.querySelector(".user-comment-item");

submitBtn.addEventListener("mouseenter", () => {
  submitBtn.style.backgroundColor = "#323ebe";
});

submitBtn.addEventListener("mouseleave", () => {
  submitBtn.style.backgroundColor = "#3B49DF";
});

cmtIcon.addEventListener("click", () => {
  userComment.scrollIntoView({ behavior: "smooth", block: "center" });
  textareaComment.focus();
});

//Functions
//Functions
//Functions
const toggleModalHidden = () => {
  overlay.classList.toggle("hidden");
  loginModal.classList.toggle("hidden");
};

function checkWordCount() {
  if (textareaComment.value == "") {
    disableSubmitBtn();
  } else {
    submitBtn.disabled = false;
    submitBtn.style.backgroundColor = "#3B49DF";
  }
}

const checkLoginKind = () => {
  if (header.getAttribute("login-kind") === "no-login") {
    likeIcon.addEventListener("click", () => {
      toggleModalHidden();
    });

    userCommentItem.addEventListener("click", () => {
      toggleModalHidden();
      textareaComment.disabled = true;
    });

    overlay.addEventListener("click", () => {
      toggleModalHidden();
    });

    closeBtnModal.addEventListener("click", () => {
      toggleModalHidden();
    });

    cmtIcon.addEventListener("click", () => {
      toggleModalHidden();
    });
  }
  if (header.getAttribute("login-kind") === "logined") {
    textareaComment.addEventListener("keyup", () => {
      checkWordCount();
    });

    submitBtn.addEventListener("click", (e) => {
      e.preventDefault();
      disableSubmitBtn();
      textareaComment.value = "";
    });

    textareaComment.addEventListener("focus", () => {
      textareaComment.style.backgroundColor = "#FFFFFF";
      textareaComment.style.outlineColor = "#3B49DF";
      submitBtn.classList.remove("hidden");
      textareaComment.style.height = "135px";
    });

    textareaComment.addEventListener("blur", () => {
      textareaComment.style.backgroundColor = "#EFEFEF";
      textareaComment.style.border = "0.5px solid #BDBDBD";
    });

    likeIcon.addEventListener("click", (e) => {
      likeIcon.classList.toggle("clicked-like-icon");
      likeIcon.nextElementSibling.classList.toggle("clicked-like-text");
    });
  }
};

const disableSubmitBtn = () => {
  submitBtn.style.backgroundColor = "#8992EC";
  submitBtn.disabled = true;
};

checkLoginKind();
disableSubmitBtn();
