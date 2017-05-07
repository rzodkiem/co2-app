export default function DataFactory($localStorage) {
    'ngInject';

    return {
        setEmission: setEmission,
        getEmission: getEmission,
        getTableData: getTableData,
        getChartData: getChartData,
        getCountryChartData: getCountryChartData,
        getCategoryChartData: getCategoryChartData
    };

    function setEmission(emission) {
        $localStorage.emission = emission;
    }

    function getEmission() {
        return $localStorage.emission;
    }

    function getTableData() {
        return $localStorage.emission.tableData;
    }

    function getChartData(aggregation) {
        console.log(aggregation);
        console.log($localStorage.emission);
        return $localStorage.emission[aggregation];
    }

    function getCountryChartData() {
        return $localStorage.emission.countryChartData;
    }

    function getCategoryChartData() {
        return $localStorage.emission.categoryChartData;
    }
}