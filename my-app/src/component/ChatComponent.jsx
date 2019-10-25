import React, { Component } from 'react'
import MessageService from '../service/MessageService.js';


class ChatComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            messages: [],
            text: ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.sendClicked = this.sendClicked.bind(this)
        this.refreshMessages = this.refreshMessages.bind(this)
    }

    componentDidMount() {
        this.getAllMessages();
        this.connect();
    }

    connect() {
        MessageService.connect().then(() => this.refreshMessages());
    }

    refreshMessages() {
        MessageService.retrieveNewMessages()
            .then(
                response => {
                    this.setState({messages : this.state.messages.concat(response.data)})
                    this.refreshMessages()
                }
            )
    }

    getAllMessages() {
        MessageService.retrieveAllMessages().then(response => {
            this.setState({messages : this.state.messages.concat(response.data)})
        })
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    sendClicked() {
        MessageService.send(this.state.text).then(() => this.setState({text : ''}));
    }


    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>Chat</h3>
                <div className="container">
                    Text: <input type="text" name="text" value={this.state.text} onChange={this.handleChange} />
                    <button className="btn btn-success" onClick={this.sendClicked}>Send</button>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Login</th>
                                <th>Message</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.messages.map(
                                    message =>
                                        <tr key={message.id}>
                                            <td>{message.sender.login}</td>
                                            <td>{message.value}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ChatComponent
