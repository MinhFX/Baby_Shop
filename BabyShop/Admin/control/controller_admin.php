<?php
$color_view1 = "";
$color_view2 = "";
?>

<?php 
    if(!isset($_GET["view"]))
{
    $color_view1 = "style='filter: invert(94%) sepia(100%) saturate(691%) hue-rotate(320deg) brightness(95%) contrast(87%);'";
}
else
    $color_view2 = "style='filter: invert(94%) sepia(100%) saturate(691%) hue-rotate(320deg) brightness(95%) contrast(87%);'";
?>