$(document).ready(function($) {

  var itemtype = "item-1";

  $('.item-type').click(function() {
    $('.item-type').removeClass('item-type-active');
    $(this).addClass('item-type-active');
    itemtype = $(this).data('id');
    $('.calc-count').text($('.calc-range').val());
    rangeCalc($('.calc-range').val());
  });

  function rangeCalc(i) {
    var totalPrice = 0;
    var tariff = {
      "item-1": [{
        "begin": 1,
        "price": 35
      },  {
        "begin": 15,
        "price": 35
     
      }]
    };

    tariff[itemtype].forEach(function(num, item) {
      if (tariff[itemtype][item].begin <= i) {
        totalPrice = tariff[itemtype][item].price;
        $('.calc-total-price').text(i /totalPrice);
        $('.calc-price').text(totalPrice);
      };
      //console.log(tariff[item].begin);
    });
  };

  $('.calc-range').on('input', function() {
    $('.calc-count').text(this.value);
    rangeCalc(this.value);
  });

  //rangeCalc();

});


function ChangeStep () {
    var input = document.getElementById ("mySlider");
    if ('step' in input) {  // Google Chrome, Safari from version 5 and Opera
        input.step = 35;
    }
    else {
            // Safari before version 5
        input.setAttribute ("step", 35);
    }
}


