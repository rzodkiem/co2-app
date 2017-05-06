export default function SessionFactory($sessionStorage) {
    var factory = {

        setSession: setSession,
        getUsername: getUsername,
        isAdmin: isAdmin

    };

    return factory;

    function setSession(session) {
        $sessionStorage.role = session.authorities[0].authority;
        $sessionStorage.username = session.name;
    }

    function getUsername() {
        return $sessionStorage.username;
    }

    function isAdmin() {
        return $sessionStorage.role === 'ROLE_ADMIN';
    }
}