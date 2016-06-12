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
    var bankId = me.data('bankId');
    var accId = me.data('accId');
    $.ajax({
      //url: "https://apisandbox.openbankproject.com/obp/v2.0.0/my/accounts",
      url: "https://apisandbox.openbankproject.com/obp/v2.0.0/banks/"+bankId+"/accounts/"+accId+"/owner/transactions",
      contentType: "application/json",
      beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'DirectLogin token="'+token+'"');},
    })
    .done(function(data, textStatus){
      //console.log(data);
      printMovements(data, bankId, accId);
    })
    .fail(function(error){
      console.log(error);
    });
  }

  function printMovements(movements, bankId, accId) {
    console.log(movements);
    movements = movements.transactions || movements;
    var container = $(".movements");
    container.find('table').remove();
    container.find('.bankId').text(bankId);
    container.find('.accId').text(accId);

    var cntMvts = $('<table class="table table-striped"></table').
      append('<thead><td>Etiqueta</td><td>Grupo contable</td><td>Contrapartida</td><td>Cantidad</td><td>Comentarios</td></thead>');
    var mvt, tags, otherAcc, amount, comments, accountingGroup;
    for (var i=0; i<movements.length; i++) {
      mvt = movements[i];
      tags = '';
      var oa;
      for (var j=0; j<mvt.metadata.tags.length; j++) {
        tags += mvt.metadata.tags[j].value;
      }

      comments = '';
      for (var j=0; j<mvt.metadata.comments.length; j++) {
        comments += mvt.metadata.comments[j].value;
      }

      oa = $('<div class="row"></div>');
      otherAcc = mvt.other_account.bank.name + '<br/>' + ( mvt.other_account.IBAN || '' ) + mvt.other_account.id;
      if (mvt.other_account.metadata && mvt.other_account.metadata.image_URL) {
        oa.append('<div class="col-md-2"><img style="max-height: 4em;" src="'+mvt.other_account.metadata.image_URL+'" /></div>');
        oa.append('<div class="col-md-10">'+otherAcc+'</div>');
      }
      else {
        oa.append('<div class="col-md-12">'+otherAcc+'</div>');
      }
      amount = mvt.details.value.amount + ' ' + mvt.details.value.currency;

      if (tags.toLowerCase().indexOf('proveedoresmateriales') != -1
          || tags.toLowerCase().indexOf('personalnominas') != -1
          || tags.toLowerCase().indexOf('proveedoresalquileres') != -1
          || tags.toLowerCase().indexOf('pagoproveedor') != -1
        ) {
          accountingGroup = '6';
      }
      else if (tags.toLowerCase().indexOf('proveedores') != -1 ) {
        accountingGroup = '4';
      }
      else if (tags.toLowerCase().indexOf('cliente') != -1 ) {
        accountingGroup = '7';
      }


      cntMvts.append('<tr><td>'+tags+'</td><td>'+accountingGroup+'</td><td>'+oa.html()+'</td><td>'+amount+'</td><td>'+comments+'</td></tr>');
    }
    container.append(cntMvts);
    container.show();
  }


  $( document ).ready(function() {
    getAccounts();
  });

}(window, jQuery));
