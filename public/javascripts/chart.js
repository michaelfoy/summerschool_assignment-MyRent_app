$(document).ready(function () {
  /**
   * Ajax call to retrieve landlord data Data consists of a list of lists, each
   * contains: list[0] landlord's name, list[1] landlord income as percentage of
   * myrent portfolio, list[2] landlord total income
   */
  $.ajax({
    url: '/getRentData',
  }).done(function (data) {
    for (let i = 0; i < data.length; i += 1)
      console.log(data[i]);
    createChart(data);
  });

  /**
   * Creates CanvasJS pie chart using landlord data
   */
  function createChart(residences) {
    let llordData = dataPoints(residences);
    $('#chart').CanvasJSChart({
      title: {
        text: 'Landlord rental income as % of entire rental portfolio',
        fontSize: 20,
      },
      legend: {
        fontFamily: 'optima',
        fontSize: 14,
        verticalAlign: 'center',
        horizontalAlign: 'right',
      },
      data: [{
        type: 'pie',
        showInLegend: true,
        toolTipContent: '{label} <br/> €{totalRent} - {y}%',
        indexLabel: '{y} %',
        dataPoints: llordData,
      },
      ],
    });
  }

  /**
   * Creates canvasjs dataPoints from the retrieved landlord data
   *
   * @param residences
   *          List of data for each landlord to be displayed in
   * @return Array of dataPoint objects for Canvasjs chart.
   */
  function dataPoints(residences) {
    let llordData = [];
    for (let i = 0; i < residences.length; i += 1) {
      llordData.push({ label: residences[i][0],
        y: Number(Math.round(residences[i][1] * 10) / 10),
        legendText: residences[i][0],
        totalRent: residences[i][2],
      });
    }

    return llordData;
  }
});
