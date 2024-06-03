var ButtonCheck = false;
var NavCheck = false;
var ImageCheck = false;
var circle_button = document.querySelector(".button");

function ButtonTrans()
{
    if (!ButtonCheck)
    {
        document.querySelector(".circle-button").style.left = "43%";
        document.querySelector(".circle-button").style.backgroundColor = "#343a40";
        document.documentElement.style.setProperty('--light', '#20304c');
        document.documentElement.style.setProperty('--light2', '#f7f7f7');
        document.documentElement.style.setProperty('--white', '#0c1a32');
        document.documentElement.style.setProperty('--chu', '#b5b5c3');
        document.documentElement.style.setProperty('--chu2', '#ffffffd9');
        document.documentElement.style.setProperty('--colormain', '#ffffffd9');
    
    }
    else
    {
        document.querySelector(".circle-button").style.left = "0";
        document.querySelector(".circle-button").style.backgroundColor = "var(--colormain)";
        document.documentElement.style.setProperty('--light', '#ececec');
        document.documentElement.style.setProperty('--light2', '#f7f7f7');
        document.documentElement.style.setProperty('--white', '#fff');
        document.documentElement.style.setProperty('--chu', '#444444');
        document.documentElement.style.setProperty('--chu2', '#555555');
        document.documentElement.style.setProperty('--colormain', '#e2c36d');
    }

    ButtonCheck = !ButtonCheck;

}

    
function NavTrans()
{
    if (!NavCheck)
    {
        document.querySelector(".Navbar-section").style.width = "5%";
        document.querySelector("#navbar-img").style.filter = "invert(100%) sepia(20%) saturate(6663%) hue-rotate(317deg) brightness(92%) contrast(90%)";
        document.querySelector(".Main-screen").style.marginLeft = "5%";
        document.querySelector(".Main-screen").style.width = "100%";
    }
    else
    {
        document.querySelector(".Navbar-section").style.width = "18%";
        document.querySelector("#navbar-img").style.filter = "none";
        document.querySelector(".Main-screen").style.marginLeft = "18%";
        document.querySelector(".Main-screen").style.width = "82%";
    }

    NavCheck = !NavCheck;
    
    function ImageChange()
    {
        if (!ImageCheck)
        {
            document.querySelector(".circle-button").style.left = "40%";
            document.querySelector(".circle-button").style.backgroundColor = "#343a40";
        }
        else
        {
            document.querySelector(".circle-button").style.left = "0";
            document.querySelector(".circle-button").style.backgroundColor = "var(--colormain)";
        }
    
        ImageCheck = !ImageCheck;
    }    
}