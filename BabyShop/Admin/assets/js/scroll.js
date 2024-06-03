window.onscroll = function(){
    navscrollFunction()
    gioithieuscrollFunction()
    dichvuscrollFunction()
};

function navscrollFunction()
{
    if (document.body.scrolltop > 500 || document.documentElement.scrollTop > 500 )
    {
        document.getElementById("header-scroll").style.top = "0";
    } else {
        document.getElementById("header-scroll").style.top = "-100px";
    }  
}

function gioithieuscrollFunction()
{
    if (document.body.scrolltop >= 500 && document.body.scrolltop <= 1500 || document.documentElement.scrollTop >= 500  && document.documentElement.scrollTop <= 1500)
    {
        document.getElementById("Gioithieu-scroll").style.animation = "slidegioithieu 0.7s ease-in";
        document.getElementById("Gioithieu-scroll").style.visibility = "visible";
    }
}

function dichvuscrollFunction()
{
    if (document.body.scrolltop >= 500 && document.body.scrolltop <= 1500 || document.documentElement.scrollTop >= 500  && document.documentElement.scrollTop <= 1500)
    {
        document.getElementById("Dichvu-scroll").style.animation = "slidedichvu 0.7s ease-in";
        document.getElementById("Dichvu-scroll").style.visibility = "visible";
    }
}

