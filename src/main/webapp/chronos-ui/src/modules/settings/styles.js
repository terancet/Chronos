const styles = () => ({
  root: {
    padding: '0px 56px',
    width: '700px',
    maxWidth: '700px',
    maxHeight: '700px',
    boxSizing: 'border-box',
    overflowY: 'hidden',
    overflowX: 'hidden'
  },
  container: {
    display: 'flex',
    flexWrap: 'wrap',
    textAlign: 'center'
  },
  formWrapper: {
    width: '50%',
    margin: '0 auto'
  },
  formControl: {
    display: 'flex',
    flexDirection: 'row',
    marginRight: '45px',
    width: '80%',
    borderRadius: '5px',
    textAlign: 'left'
  },
  formControlSelect: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    margin: '16px 0px'
  },
  formControlDeleted: {
    margin: '0px'
  },
  inputTextDeleted: {
    margin: '0px'
  },
  deletedText: {
    color: '#ffffff'
  },
  deletedBox: {
    paddingRight: '6px',
    width: '10px'
  },
  labelTextDeleted: {
    top: '-30px',
    left: '10px',
    fontSize: '14px',
    backgroundColor: '#ffffff',
    zIndex: '1'
  },
  textFieldLabel: {
    color: '#4a4a4a'
  },
  textFieldLabelFocused: {
    color: 'rgba(173, 181, 199, 0.87)'
  },
  textFieldOutlinedInput: {
    width: '257px',
    $textFieldFocusedNotchedOutline: {
      borderColor: '#25cdda'
    },
    '&:hover $textFieldFocusedNotchedOutline': {
      borderColor: 'rgba(173, 181, 199, 0.87) !important'
    }
  },
  selectOutlinedInput: {
    width: '100%',
    $textFieldFocusedNotchedOutline: {
      borderColor: '#25cdda'
    },
    '&:hover $textFieldFocusedNotchedOutline': {
      borderColor: 'rgba(173, 181, 199, 0.87) !important'
    }
  },
  inputText: {
    width: '100%'
  },
  selectColor: {
    minWidth: '120px'
    // fontSize: '0px'
  },
  textFieldFocusedNotchedOutline: {
    borderWidth: '2px',
    borderColor: 'rgba(173, 181, 199, 0.87)',
    '&:hover': {
      borderWidth: '2px',
      borderColor: 'rgba(173, 181, 199, 0.87)'
    }
  },
  labelText: {
    top: '-8px',
    left: '15px'
  },
  controlBtn: {
    marginTop: '25px',
    padding: '0 20px',
    height: '42px',
    backgroundColor: '#25cdda',
    color: '#ffffff',
    fontWeight: '400',
    textTransform: 'capitalize'
  },
  saveBtnDisabled: {
    width: '140px',
    border: '1px solid #25cdda',
    color: '#25cdda',
    fontWeight: '500',
    backgroundColor: 'transparent',
    '&:hover': {
      backgroundColor: 'transparent !important'
    }
  },
  deleteBtn: {
    paddingLeft: '0px',
    marginRight: '20px',
    color: '#ed5454',
    '&:hover': {
      backgroundColor: 'transparent !important'
    }
  },
  navTitle: {
    margin: '40px 0px 20px 0px',
    textAlign: 'center',
    fontSize: '20px'
  },
  selectItemColor: {
    fontSize: '0px'
  }
});

export default styles;
