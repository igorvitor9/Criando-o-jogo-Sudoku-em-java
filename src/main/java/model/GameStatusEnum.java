package model;

public enum GameStatusEnum {
    NOW_STARTED("Não iniciado"),
    INCOMPLETE("Não completo"),
    COMPLETE("Completo");

    private String label;

    GameStatusEnum(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
