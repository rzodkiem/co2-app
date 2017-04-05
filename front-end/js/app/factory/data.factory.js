export default function DataFactory($localStorage) {
    'ngInject';
    var data,
        factory = {
            setEmission: setEmission,
            getEmission: getEmission
        };
    return factory;

    function setEmission(emission) {
        $localStorage.emission = emission;
    }

    function getEmission() {
        return $localStorage.emission;
    }
}