var toggle = document.getElementById('darkmode');
var body = document.querySelector('body');
var header = document.querySelector('navbar');

var subtitle = document.getElementById('subtitle');

var footer = document.getElementById('footer');
var sky = document.getElementById('sky');


toggle.onclick = function () {
    toggle.classList.toggle('active');
    body.classList.toggle('active');

    navbar.classList.toggle('active');
    subtitle.classList.toggle('active');

    footer.classList.toggle('active');
    sky.classList.toggle('active');
}