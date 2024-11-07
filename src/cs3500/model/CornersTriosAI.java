package cs3500.model;

public class CornersTriosAI implements TriosAI<ThreeTriosCard>{
    @Override
    public int[] findMove(TriosModel model, Player player) {

        int[] bestMove = new int[3];

        if (model.getStatusBoard()[0][0].equals(Status.EMPTY)){
//            for (Object card : model.getHand(player)){
//                card.
//            }
            
        } else if (model.getStatusBoard()[model.getStatusBoard().length][0].equals(Status.EMPTY)) {
            
        } else if (model.getStatusBoard()[0][model.getStatusBoard()[0].length].equals(Status.EMPTY)){
            
        } else if (model.getStatusBoard()[model.getStatusBoard().length][model.getStatusBoard()[0].length].equals(Status.EMPTY)) {
            
        }

        return new int[0];
    }
}
