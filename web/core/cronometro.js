var intervalo;
var segundos = 0;
var pontuacao = 0;

function tempo(op) {
        var secondtime = 0;
        var pontos = 12.30;
	var s = 1;
	var m = 0;
	var h = 0;
	intervalo = window.setInterval(function() {
		if (s === 60) { m++; s = 0; }
		if (m === 60) { h++; s = 0; m = 0; }
		if (h < 10) document.getElementById("hora").innerHTML = "0" + h + ":"; else document.getElementById("hora").innerHTML = secondtime + ":";
		if (s < 10) document.getElementById("segundo").innerHTML = "0" + s + " "; else document.getElementById("segundo").innerHTML = s + " ";
		if (m < 10) document.getElementById("minuto").innerHTML = "0" + m + ":"; else document.getElementById("minuto").innerHTML = m + ":";		
                s++;
                document.getElementById('pontos').innerHTML=" " + parseInt((pontos/secondtime)*100000) + " pontos";
                document.getElementById('tempo').innerHTML=" " + secondtime + " pontos";
                segundos = secondtime;
                pontuacao = parseInt((pontos/secondtime)*100000);
                secondtime++;
	},1000);
}

function parar() {
	window.clearInterval(intervalo);
}
