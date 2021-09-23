firebase.initializeApp({
  apiKey: 'AIzaSyAPgZZxNDsNeVB-C6hMGKzsFelsBRIjdBI',
  authDomain: 'udemy-vue-firebase-si.firebaseapp.com',
  projectId: 'udemy-vue-firebase-si',
  storageBucket: 'udemy-vue-firebase-si.appspot.com',
  messagingSenderId: '856454367038',
  appId: '1:856454367038:web:85b33106a4383ec36b0e6f',
});

const uuidv4 = () => {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
    const r = (Math.random() * 16) | 0;
    const v = c === 'x' ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
};

async function uploadImage(pictureBase64, name, id) {
  let filePath = null;
  filePath = `avatar-user/${id}/${name}`;
  var storageRef = firebase.storage().ref(filePath);
  try {
    const res = await storageRef.putString(pictureBase64, 'data_url', {
      contentType: 'image/jpg',
    });
    let url = await res.ref.getDownloadURL();
    $('#avatarURL').attr('value', url);
    console.log(url);
    return { url };
  } catch (err) {
    console.log(err);
  }
}

$(document).ready(function () {
  $('#file-input-avatar').change(function () {
    const imgBlob = document.getElementById('file-input-avatar');
    if (imgBlob.files && imgBlob.files[0]) {
      let reader = new FileReader();
      reader.onload = function (e) {
        uploadImage(e.target.result, imgBlob.files[0].name, uuidv4());
        $('#img-preview').attr('src', e.target.result);
      };
      reader.readAsDataURL(imgBlob.files[0]);
    }
  });
});

const form = document.querySelector('form');
form.addEventListener('submit', (event) => {
  event.preventDefault();
  let name = document.getElementById('name');
  let email = document.getElementById('email');
  let password = document.getElementById('password');
  let passwordRepeat = document.getElementById('passwordRepeat');
  if (
    name.value.length < 1 ||
    email.value.length < 1 ||
    password.value.length < 1 ||
    passwordRepeat.value.length < 1
  ) {
    swal('Please fill the blank', '', 'error', {
      button: 'Ok',
    });
  } else {
    if (password.value.length < 6 || passwordRepeat.value.length < 6) {
      swal('Password must longer than 6!', '', 'error', {
        button: 'Ok',
      });
    } else {
      if (password.value.length == passwordRepeat.value.length) {
        form.submit();
      } else {
        swal('Password does not match!', '', 'error', {
          button: 'Ok',
        });
      }
    }
  }
});
