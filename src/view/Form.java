package view;

public interface Form {
//    final String INVALID_MESSAGE = "Some fields mightbe empty";

    final String INVALID_MESSAGE = "Please check you entered all information and they are correct";
    final String INVALID_TITLE = "Incorrect information format";
    final String SUCCESS_MESSAGE = "Updated Successfully!";
    final String CONFIRM_DELETE_MESSAGE = "Deleted records cannot be recovered back.\nDelete any way?";
    final String CONFIRM_DELETE_TITLE = "Deleting record";

    /**
     *
     * @param b make all buttons foggy: jButton.setEnabled(!b);
     */
    void setBusy(boolean b);

    void txtClear();
}
