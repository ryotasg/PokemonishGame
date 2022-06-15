package client;

import java.io.IOException;

public class ConsoleClient extends Thread {
    Client client;

    public static void main(String[] args) {
        ConsoleClient cClient = new ConsoleClient();
        cClient.run();
    }

    public void run() {
        try {
            client = new Client();
            client.connect();
            client.recieveAndLog(3); //接続確認応答を受け取る 
            client.nameInputAndAck();
            client.logging("あなたのモンスターを生成します");
            client.recieveAndLog(10); // 生成したモンスターのデータを受け取る
            client.logging("対戦相手を探しています");
            client.recieveAndLog(2); // 対戦相手を表示
            client.logging("対戦開始");
            client.initTurn(client.recieve()); //先攻か後攻かを受け取ってターンを初期化
            client.inBattle();
            client.showBattleResult();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
