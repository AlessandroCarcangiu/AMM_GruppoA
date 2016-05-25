/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function verifica(event)
{
    var select = document.getElementById("select");
    var gusti = [];
    
    for(var i = 0; i < select.options.length; i++)
    {
        if(select.options[i].selected)
            gusti.push(select.options[i].value);
    }
    
    var gelato = new Gelato(gusti);
    
    if(gelato.numeroGusti() > 3 || gelato.numeroGusti()<1)
        document.getElementById("risultato_ordinazione")
            .innerHTML = "Errore! Hai seleziona o troppi gusti o \n\
                          troppo pochi";
    else if(gelato.contieneCrudo() && gelato.numeroGusti() > 1)
        document.getElementById("risultato_ordinazione")
            .innerHTML = "Errore! Con il crudo di parma non\n\
                          puoi selezionare altri gusti!";
    else
        document.getElementById("risultato_ordinazione")
            .innerHTML = "Ecco il tuo gelato!";
}

function Gelato(g)
{
    var gusti = g;
    
    Gelato.prototype.numeroGusti = function()
    {
        return gusti.length;
    };
    
    Gelato.prototype.contieneCrudo = function()
    {
        for(var i = 0; i < gusti.length; i++)
        {
            if(gusti[i] === "crudo di parma")
                return true;
        }
        return false;
    };
}
