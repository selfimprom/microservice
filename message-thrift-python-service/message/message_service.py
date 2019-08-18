from message.api import MessageService
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer


class MessageServiceHandler:
    def sendMobileMessage(self, mobile, message):
        print("sendMobileMessage" + mobile + " message:" + message)
        return True


def sendEmailMessage(self, email, message):
    print("sendEmailMessage email:" + email + " message:" + message)

    return True


if __name__ == '__main__':
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)
    transport = TSocket.TServerSocket("127.0.0.1", "9090")
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print("pthon thrift server start")
    server.serve()
    print("python thrift server exit")
