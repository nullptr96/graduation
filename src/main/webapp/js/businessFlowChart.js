function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

        if(arr=document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }
var userId = getCookie('userId');
if(userId == null){
    //todo: logout
}
var flowdata={ "class": "go.GraphLinksModel",
    "linkFromPortIdProperty": "fromPort",
    "linkToPortIdProperty": "toPort",
    "nodeDataArray": [
        // {"category":"Comment", "loc":"360 -10", "text":"comment", "key":-13},
        // {"key":-1, "category":"Start", "loc":"175 0", "text":"Start"},
        // {"key":0, "loc":"0 0", "text":"node1"},
        // {"key":1, "loc":"175 100", "text":"node2","input":"周一","output":"火车"},
        // {"key":-2, "category":"End", "loc":"175 640", "text":"End!"}
    ],
    "linkDataArray": [
        // {"from":1, "to":2, "fromPort":"B", "toPort":"T"}
    ]};
var choosedNodeData;
var _designer = {};
function FlowDesigner(diagramDiv) {
    var G = go.GraphObject.make;
    var _this = {};
    // var _designer = {};
    var _jsonNewStep = { key: guid(), text: '_jsonNewStep', remark: '' };

    /** --------public method----------------------------------------**/
    /**
     * ��ʼ��ͼ�����
     * @returns {*}
     */
    this.initToolbar = function(div){
        var myPalette = G(go.Palette, div,  // must name or refer to the DIV HTML element
            {
                // scrollsPageOnFocus: false,
                nodeTemplateMap: _designer.nodeTemplateMap,  // share the templates used by myDiagram
                model: new go.GraphLinksModel([  // specify the contents of the Palette
                    { type:'start', category: 'Start', text: 'Start' },
                    { type:'operation',text: 'Step' },
                    { type:'operation',category: 'Stress', text: 'Step' },
                    { type:'condition',text: '条件 ', figure: 'Diamond' },
                    { type:'end',category: 'End', text: 'End' },
                    { type:'comment',category: 'Comment', text: 'Comment' }
                ])
            });

        return myPalette;
    };

    /**
     * ������������ʾ����ͼ
     * @param flowData  ����ͼjson����
     */
    this.displayFlow = function (flowData) {

        if(!flowData) return;

        _designer.model = go.Model.fromJson(flowData);

        var pos = _designer.model.modelData.position;
        if (pos) _designer.initialPosition = go.Point.parse(pos);

        // �������������м���ı�����ɫ
        setLinkTextBg();
    };
    /** --------public method-------------end---------------------------**/

    init(diagramDiv);

    /** --------private method----------------------------------------**/

    /**
     * ��ʼ�����������
     * @param divId �����Div
     */
    function init(divId) {
        _designer = G(go.Diagram, divId, // must name or refer to the DIV HTML element
            {
                grid: G(go.Panel, 'Grid',
                    G(go.Shape, 'LineH', { stroke: '#EDEDED', strokeWidth: 0.5 }),
                    G(go.Shape, 'LineH', { stroke: '#D2D2D2', strokeWidth: 0.5, interval: 10 }),
                    G(go.Shape, 'LineV', { stroke: '#EDEDED', strokeWidth: 0.5 }),
                    G(go.Shape, 'LineV', { stroke: '#D2D2D2', strokeWidth: 0.5, interval: 10 })
                ),
                'initialContentAlignment': go.Spot.Center,
                'allowDrop': true,  // must be true to accept drops from the Palette
                // 'clickCreatingTool.archetypeNodeData': _jsonNewStep, // ˫�������²���
                // 'LinkDrawn': showLinkLabel,  // this DiagramEvent listener is defined below
                // 'LinkRelinked': showLinkLabel,
                'TextEdited': showLinkLabel,
                // 'scrollsPageOnFocus': false,
                // 'draggingTool.dragsLink': true,
                // 'draggingTool.isGridSnapEnabled': true,
                // 'linkingTool.isUnconnectedLinkValid': true,
                // 'linkingTool.portGravity': 20,
                // 'relinkingTool.isUnconnectedLinkValid': true,
                // 'relinkingTool.portGravity': 20,
                'undoManager.isEnabled': true,  // enable undo & redo
                // 'commandHandler': G(DrawCommandHandler)
            });

        // ����ͼ����б䶯������ʾ�û�����
        _designer.addDiagramListener('Modified', onDiagramModified);

        // ˫���¼�
        _designer.addDiagramListener('ObjectDoubleClicked', onObjectDoubleClicked);
        _designer.addDiagramListener('ObjectSingleClicked', onObjectSingleClicked);
        _designer.addDiagramListener('ObjectContextClicked', onObjectContextClicked);
        _designer.addDiagramListener('TextEdited', onTextEdited);
        _designer.addDiagramListener('PartCreated', onPartCreated);
        _designer.addDiagramListener('LinkDrawn', onLinkDrawn);
        _designer.addDiagramListener('SelectionDeleted', onSelectionDeleted);
        _designer.addDiagramListener('SelectionMoved', onSelectionMoved);
        _designer.addModelChangedListener(function(evt) {
            // ignore unimportant Transaction events
            if (!evt.isTransactionFinished) return;
            var txn = evt.object;  // a Transaction
            if (txn === null) return;
            // iterate over all of the actual ChangedEvents of the Transaction
            txn.changes.each(function(e) {
                // ignore any kind of change other than adding/removing a node
                if (e.modelChange !== "nodeDataArray") return;
                // record node insertions and removals
                if (e.change === go.ChangedEvent.Insert) {
                    alert('new node');
                    e.getValue().input='';//给节点添加输入参数属性
                    e.getValue().output='';//给节点添加输出参数属性
                } else if (e.change === go.ChangedEvent.Remove) {
                    alert('delete node');
                }
            });
        });

        // ���̲������ʽģ��
        makeNodeTemplate();

        // ���������ߵ���ʽģ��
        _designer.linkTemplate = makeLinkTemplate();

        _designer.contextMenu = makeContextMenu();

        // temporary links used by LinkingTool and RelinkingTool are also orthogonal:
        _designer.toolManager.linkingTool.temporaryLink.routing = go.Link.Orthogonal;
        _designer.toolManager.relinkingTool.temporaryLink.routing = go.Link.Orthogonal;
    };

    /**
     * ����GUID
     * @returns {string}
     */
    function guid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    // Make link labels visible if coming out of a 'conditional' node.
    // This listener is called by the 'LinkDrawn' and 'LinkRelinked' DiagramEvents.
    function showLinkLabel(e) {
        var textbox = e.subject;
        var label = e.subject.panel;
        if (label !== null) {
            label.visible = (textbox.text != '');
        }
    };

    /**
     * ����ͼ����б䶯������ʾ�û�����
     * @param e
     */
    function onDiagramModified(e) {
        alert("modified");
        // var button = document.getElementById('btnsaveflow');//这个BT已经删了
        // if (button) button.disabled = !_designer.isModified;
        // var idx = document.title.indexOf('*');
        // if (_designer.isModified) {
        // 	if (idx < 0) document.title += '*';
        // } else {
        // 	if (idx >= 0) document.title = document.title.substr(0, idx);
        // }
        // alert(e.toString());

    };

    /**
     * ѡ�нڵ����ʽ
     * @returns {*}
     */
    function makeNodeSelectionAdornmentTemplate(){
        return G(go.Adornment, 'Auto',
            G(go.Shape, { fill: null, stroke: '#1E90FF', strokeWidth: 2, strokeDashArray: [4, 2] }),
            G(go.Placeholder)
        );
    }

    /**
     * ����ͼ����ʽģ��
     * @returns {*}
     */
    function makeNodeTemplate() {

        // Make all ports on a node visible when the mouse is over the node
        var showPorts = function(node, show) {
            var diagram = node.diagram;
            if (!diagram || diagram.isReadOnly || !diagram.allowLink) return;
            node.ports.each(function(port) {
                port.stroke = (show ? '#DC3C00' : null);
            });
        }
        // helper definitions for node templates
        var nodeStyle = function() {
            return [
                // ѡ�п���ʽ
                { selectable: true, selectionAdornmentTemplate: makeNodeSelectionAdornmentTemplate() },
                // The Node.location comes from the 'loc' property of the node data,
                // converted by the Point.parse static method.
                // If the Node.location is changed, it updates the 'loc' property of the node data,
                // converting back using the Point.stringify static method.
                new go.Binding('location', 'loc', go.Point.parse).makeTwoWay(go.Point.stringify),
                {
                    // the Node.location is at the center of each node
                    locationSpot: go.Spot.Center,
                    //isShadowed: true,
                    //shadowColor: '#888',
                    // handle mouse enter/leave events to show/hide the ports
                    mouseEnter: function (e, obj) { showPorts(obj.part, true); },
                    mouseLeave: function (e, obj) { showPorts(obj.part, false); }
                }
            ];
        }

        // Define a function for creating a 'port' that is normally transparent.
        // The 'name' is used as the GraphObject.portId, the 'spot' is used to control how links connect
        // and where the port is positioned on the node, and the boolean 'output' and 'input' arguments
        // control whether the user can draw links from or to the port.
        var makePort = function(name, spot, output, input) {
            // the port is basically just a small circle that has a white stroke when it is made visible
            return G(go.Shape, 'Circle',
                {
                    fill: 'transparent',
                    stroke: null,  // this is changed to 'white' in the showPorts function
                    desiredSize: new go.Size(8, 8),
                    alignment: spot, alignmentFocus: spot,  // align the port on the main Shape
                    portId: name,  // declare this object to be a 'port'
                    fromSpot: spot, toSpot: spot,  // declare where links may connect at this port
                    fromLinkable: output, toLinkable: input,  // declare whether the user may draw links to/from here
                    cursor: 'pointer'  // show a different cursor to indicate potential link point
                });
        }
        // define the Node templates for regular nodes
        var lightText = 'whitesmoke';
        _designer.nodeTemplateMap.add('',  // the default category
            G(go.Node, 'Spot', nodeStyle(),
                // the main object is a Panel that surrounds a TextBlock with a rectangular Shape
                G(go.Panel, 'Auto',
                    G(go.Shape, 'RoundedRectangle',
                        { fill: '#F7F7F7', strokeWidth: 2, stroke: '#979797' },
                        new go.Binding('figure', 'figure')),
                    G(go.TextBlock,
                        {
                            font: '14px "Microsoft YaHei", Helvetica, Arial, sans-serif',
                            stroke: '#464646',
                            margin: 8,
                            maxSize: new go.Size(160, NaN),
                            wrap: go.TextBlock.WrapFit,
                            editable: true
                        },
                        new go.Binding('text').makeTwoWay())
                ),
                // four named ports, one on each side:
                makePort('T', go.Spot.Top, false, true),
                makePort('L', go.Spot.Left, true, true),
                makePort('R', go.Spot.Right, true, true),
                makePort('B', go.Spot.Bottom, true, false)
            ));
        _designer.nodeTemplateMap.add('Stress',  // the default category
            G(go.Node, 'Spot', nodeStyle(),
                // the main object is a Panel that surrounds a TextBlock with a rectangular Shape
                G(go.Panel, 'Auto',
                    G(go.Shape, 'RoundedRectangle',
                        { fill: '#F7F7F7', strokeWidth: 2, stroke: '#DC3C00' },
                        new go.Binding('figure', 'figure')),
                    G(go.TextBlock,
                        {
                            font: '14px "Microsoft YaHei", Helvetica, Arial, sans-serif',
                            stroke: '#464646',
                            margin: 8,
                            maxSize: new go.Size(160, NaN),
                            wrap: go.TextBlock.WrapFit,
                            editable: true
                        },
                        new go.Binding('text').makeTwoWay())
                ),
                // four named ports, one on each side:
                makePort('T', go.Spot.Top, false, true),
                makePort('L', go.Spot.Left, true, true),
                makePort('R', go.Spot.Right, true, true),
                makePort('B', go.Spot.Bottom, true, false)
            ));
        _designer.nodeTemplateMap.add('Start',
            G(go.Node, 'Spot', nodeStyle(),
                G(go.Panel, 'Auto',
                    G(go.Shape, 'Circle',
                        { minSize: new go.Size(40, 40), fill: '#79C900', stroke: null }),
                    G(go.TextBlock, 'Start',
                        { font: '12px "Microsoft YaHei", Helvetica, Arial, sans-serif', stroke: lightText },
                        new go.Binding('text'))
                ),
                // three named ports, one on each side except the top, all output only:
                makePort('L', go.Spot.Left, true, false),
                makePort('R', go.Spot.Right, true, false),
                makePort('B', go.Spot.Bottom, true, false)
            ));
        _designer.nodeTemplateMap.add('End',
            G(go.Node, 'Spot', nodeStyle(),
                G(go.Panel, 'Auto',
                    G(go.Shape, 'Circle',
                        { minSize: new go.Size(40, 40), fill: '#DC3C00', stroke: null }),
                    G(go.TextBlock, 'End',
                        { font: '12px "Microsoft YaHei", Helvetica, Arial, sans-serif', stroke: lightText },
                        new go.Binding('text'))
                ),
                // three named ports, one on each side except the bottom, all input only:
                makePort('T', go.Spot.Top, false, true),
                makePort('L', go.Spot.Left, false, true),
                makePort('R', go.Spot.Right, false, true)
            ));
        _designer.nodeTemplateMap.add('Comment',
            G(go.Node, 'Auto', nodeStyle(),
                G(go.Shape, 'File',
                    { fill: '#FFFFCC', stroke: '#E7E7B0' }),
                G(go.TextBlock,
                    {
                        margin: 5,
                        maxSize: new go.Size(200, NaN),
                        wrap: go.TextBlock.WrapFit,
                        textAlign: 'center',
                        editable: true,
                        font: '12px "Microsoft YaHei", Helvetica, Arial, sans-serif',
                        stroke: '#454545'
                    },
                    new go.Binding('text').makeTwoWay())
                // no ports, because no links are allowed to connect with a comment
            ));
    };

    /**
     * ���������ߵ���ʽģ��
     * @returns {*}
     */
    function makeLinkTemplate() {
        return G(go.Link,  // the whole link panel
            {
                routing: go.Link.AvoidsNodes,
                curve: go.Link.JumpOver,
                corner: 5, toShortLength: 4,
                relinkableFrom: true,
                relinkableTo: true,
                reshapable: true,
                resegmentable: true,
                // mouse-overs subtly highlight links:
                mouseEnter: function(e, link) { link.findObject('HIGHLIGHT').stroke = 'rgba(30,144,255,0.2)'; },
                mouseLeave: function(e, link) { link.findObject('HIGHLIGHT').stroke = 'transparent'; }
            },
            new go.Binding('points').makeTwoWay(),
            G(go.Shape,  // the highlight shape, normally transparent
                { isPanelMain: true, strokeWidth: 8, stroke: 'transparent', name: 'HIGHLIGHT' }),
            G(go.Shape,  // the link path shape
                { isPanelMain: true, stroke: '#ADADAD', strokeWidth: 1 }),
            G(go.Shape,  // the arrowhead
                { toArrow: 'standard', stroke: null, fill: '#ADADAD'}),

            G(go.Panel, 'Auto',  // the link label, normally not visible
                { visible: false, name: 'LABEL', segmentIndex: 2, segmentFraction: 0.5}, // ����λ�� segmentOffset: new go.Point(0, 10)
                new go.Binding('visible', 'visible').makeTwoWay(),
                G(go.Shape, 'RoundedRectangle',  // the label shape
                    { fill: '#EAEAEA', stroke: null }), //'#F8F8F8' '#E4E4E4'
                G(go.TextBlock, // the label 'Yes',
                    {
                        name: 'TEXTBLOCK',
                        textAlign: 'center',
                        font: '10px "Microsoft YaHei", Helvetica, arial, sans-serif',
                        stroke: '#A0A0A0',
                        editable: true
                    },
                    new go.Binding('text').makeTwoWay())
            )
        );
    };

    function makeContextMenu() {
        return G(go.Adornment, 'Vertical',
            G('ContextMenuButton',
                G(go.TextBlock, 'Undo'),
                { click: function(e, obj) { e.diagram.commandHandler.undo(); } },
                new go.Binding('visible', '', function(o) {
                    return o.diagram.commandHandler.canUndo();
                }).ofObject()),
            G('ContextMenuButton',
                G(go.TextBlock, 'Redo'),
                { click: function(e, obj) { e.diagram.commandHandler.redo(); } },
                new go.Binding('visible', '', function(o) {
                    return o.diagram.commandHandler.canRedo();
                }).ofObject())
        );
    }

    function setLinkTextBg() {
        //!!! TODO
        return;
        _designer.links.each(function (link) {
            console.log(link.data);
            _designer.startTransaction('vacate');
            if (link.data.text) {
                _designer.model.setDataProperty(link.data, 'pFill', go.GraphObject.make(go.Brush, 'Radial', {
                    0:   'rgb(240, 240, 240)',
                    0.3: 'rgb(240, 240, 240)',
                    1:   'rgba(240, 240, 240, 0)'
                }));
            }
            _designer.commitTransaction('vacate');
        });
    };

    function onObjectDoubleClicked(ev) {
        // alert("double clicked");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.toString());
        if (part instanceof go.Link && !part.data.text) {
            // console.log(part.data);
            // console.log(part.data.text);
            updateNodeData(part, 'Yes');
            part.findObject('LABEL').visible = true;
        }
    };

    function onObjectSingleClicked(ev){
        // alert(" Single clicked");
        var part = ev.subject.part;
        choosedNodeData = ev.subject.part.data;
        document.getElementById('nodeAttribute-input').value = choosedNodeData.input;
        document.getElementById('nodeAttribute-output').value = choosedNodeData.output;
        document.getElementById('nodeAttribute-desc').value = choosedNodeData.text;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function onObjectContextClicked(ev){
        // alert(" context clicked");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function onTextEdited(ev){
        // alert(" TextEdited");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function onPartCreated(ev){
        alert(" onPartCreated");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function onLinkDrawn(ev){
        alert(" onLinkDrawn");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function onSelectionDeleted(ev){
        alert(" onSelectionDeleted");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function onSelectionMoved(ev){
        alert(" onSelectionMoved");
        var part = ev.subject.part;
        // showEditNode(part);
        // console.log(part.data.text);
        // alert(part.data.text);
    }

    function updateNodeData(node, text) {
        _designer.startTransaction('vacate');
        _designer.model.setDataProperty(node.data, 'text', text);
        _designer.commitTransaction('vacate');
    };

    /** --------private method------------------end----------------------**/
    return this;
}
$(function(){
    $('#diagramdiv').height($(window).height()-60);
    var areaFlow = $('#savedmodel').get(0);
    var  myDesigner= new FlowDesigner('diagramdiv');
    myDesigner.initToolbar('palettediv'); // ��ʼ���ؼ����
    // myDesigner.displayFlow(areaFlow.value); // ������������ʾ����ͼ
    myDesigner.displayFlow(flowdata); // ������������ʾ����ͼ
});

function saveNode(){
    choosedNodeData.input = document.getElementById('nodeAttribute-input').value;
    choosedNodeData.output = document.getElementById('nodeAttribute-output').value;
    choosedNodeData.text = document.getElementById('nodeAttribute-desc').value;
    _designer.model.updateTargetBindings(choosedNodeData) //更新节点数据
}


function  test() {
    console.log('hello');
}

function saveBF(){
    var params = {};
    params.nodeDataArray = JSON.stringify(_designer.model.nodeDataArray);
    params.linkDataArray = JSON.stringify(_designer.model.linkDataArray);//注意params.名称  名称与实体bean中名称一致
    $.ajax({
        type: "POST",
        url: "../bussinessFlow/saveBF",
        data: params,
        dataType:"json",
//	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.msg != ""){
                alert( data.msg );
            }
        },
        error:function(data){
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
}

function generateWSF(){
    var params = {};
    params.nodeDataArray = _designer.model.nodeDataArray;
    params.linkDataArray = _designer.model.linkDataArray;//注意params.名称  名称与实体bean中名称一致
    $.ajax({
        type: "POST",
        url: "../bussinessFlow/generateWSF",
        data:params,
        dataType:"json",
//	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data === true){
                alert( "生成成功" );
            }
        },
        error:function(data){
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
}