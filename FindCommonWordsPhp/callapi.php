
<?php

class CommonWordsApp {

    public function __construct() {
        echo "\nconstructor called..";
    }

    public function getCommonWords() {

        echo "\n".$_POST["record"]." - ".$_POST["pageno"];
        echo "\nfunction called";
        $path = "path=/Users/abhishek/Documents/project";
        $url="localhost:9098/find-words";

        try {
            $ch = curl_init();
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $path);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Connection: Keep-Alive'
            ));
            $output = curl_exec($ch);
            echo "\nResponse from API = ".$output."\n";
        } catch (Exception $e) {
            echo "Error = ".$e->getMessage();
        } finally {
            curl_close($ch);
        }
    }
}

?>

</body>
</html>