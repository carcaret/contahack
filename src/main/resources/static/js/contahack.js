(function (window, $) {

  var token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.r0wU2VytaeLUPDPtsdzjqOzqy0HSitkFNn3V2AnrB48';

  function getAccounts() {
    $.ajax({
      url: "https://apisandbox.openbankproject.com/obp/v2.0.0/my/accounts",
      contentType: "application/json",
      beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'DirectLogin token="'+token+'"');},
    })
    .done(function(data, textStatus){
      //console.log(data);
      printAccounts(data);
    })
    .fail(function(error){
      console.log(error);
    });
  }

  function printAccounts(accounts){
    //console.log(accounts);
    var container = $(".accounts");
    container.append("<ul></ul>");

    var acc, li;
    for (var i=0; i < accounts.length; i++) {
      acc = accounts[i];
      li = $('<li class="account">'+(acc.label || acc.id)+"</li>")
        .data('bankId', acc.bank_id)
        .data('accId', acc.id)
        .click(function(){
          loadMovements($(this));
          //console.log($(this).data('accId'));
        });
      container.append(li);
    }
  }

  function loadMovements(me) {
    $.ajax({
      //url: "https://apisandbox.openbankproject.com/obp/v2.0.0/my/accounts",
      url: "https://apisandbox.openbankproject.com/obp/v2.0.0/banks/"+me.data('bankId')+"/accounts/"+me.data('accId')+"/owner/transactions",
      contentType: "application/json",
      beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'DirectLogin token="'+token+'"');},
    })
    .done(function(data, textStatus){
      //console.log(data);
      printMovements(data);
    })
    .fail(function(error){
      console.log(error);
    });
  }

  function printMovements(movements) {
    console.log(movements);
    var container = $(".movements");
    var cntMvts = $("<ul></ul>");
    container.append();
  }


  $( document ).ready(function() {
    getAccounts();
  });

}(window, jQuery));
