let quantita = 1;
function qty(click){
    if(click=="plus"){
        quantita ++;
        document.querySelector(".number").innerText = quantita;
    } else {
        if(quantita != 1){
            quantita --;
            document.querySelector(".number").innerText = quantita;
        }
    }
}