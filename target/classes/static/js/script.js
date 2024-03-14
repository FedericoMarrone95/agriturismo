let quantità = 1;
function qty(click){
    if(click=="plus"){
        quantità ++;
        document.querySelector(".number").innerText = quantità;
    } else {
        if(quantità != 1){
            quantità --;
            document.querySelector(".number").innerText = quantità;
        }
    }
}