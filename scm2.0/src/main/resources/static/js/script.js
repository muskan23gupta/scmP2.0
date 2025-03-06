console.log("Script Loaded");

//start of change theme work 
let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
})
changeTheme(currentTheme)

function changeTheme(){
    //set to web page
    changePageTheme(currentTheme,currentTheme);
//console.log(currentTheme);
const changeThemeButton = document.querySelector("#theme_change_button");
currentTheme == "Light" ? "Dark" : "Light";
const oldTheme = currentTheme;
changeThemeButton.addEventListener("click", (event) => {
    console.log("change theme button clicked");
    if(currentTheme === "dark"){
        currentTheme = "light";
    } 
    else{
        currentTheme = "dark";
    } 
    //updating it in a localstorage

    changePageTheme(currentTheme, oldTheme); 
});
}

//set theme to localstorage (localstorage is a storage that stores the data & whoseobject is available to us)

function setTheme(theme){
    localStorage.setItem("theme", theme);
}

//get theme from localstorage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

//change current page theme method
function changePageTheme(theme,oldTheme){
    setTheme(currentTheme);
    //removethe current theme
    document.querySelector("html").classList.remove(oldTheme);
    //set the current theme
    document.querySelector("html").classList.add(theme);
    //change the text of button 
    document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent = theme == 'light' ? "Dark" : "Light";
}

//end of change theme work